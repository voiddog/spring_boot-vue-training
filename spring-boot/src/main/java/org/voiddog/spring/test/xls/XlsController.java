package org.voiddog.spring.test.xls;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.voiddog.spring.test.model.Constants;
import org.voiddog.spring.test.model.MessageException;
import org.voiddog.spring.test.model.Response;
import org.voiddog.spring.test.student.StudentRepository;
import org.voiddog.spring.test.teacher.TeacherDAO;
import org.voiddog.spring.test.teacher.TeacherRepository;
import org.voiddog.spring.test.xls.reader.teacher.XlsTeacherReader;
import org.voiddog.spring.test.xls.reader.teacher.XlsTeacherWriter;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/xls")
public class XlsController {

    private static Logger logger = LoggerFactory.getLogger("Xls");

    @Autowired
    private XlsRepository repository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(path = "/upload/teacher", method = RequestMethod.POST)
    public Response<XlsFileDAO> upload(@RequestParam("file") MultipartFile file){
        XlsFileDAO dao = filterFile(file);
        if (dao == null) {
            throw new MessageException("预处理xls文件失败");
        }
        try {
            InputStream xlsStream = new FileInputStream(new File(dao.getFilePath()));
            Workbook wb;
            if (dao.getFilePath().toLowerCase().endsWith("xlsx")) {
                wb = new XSSFWorkbook(xlsStream);
            } else {
                wb = new HSSFWorkbook(xlsStream);
            }

            Sheet sheet = wb.getSheetAt(0);
            if (sheet == null) {
                throw new MessageException("表格为空");
            }
            Row row = sheet.getRow(0);
            if (row == null) {
                throw new MessageException("属性栏为空");
            }
            XlsTeacherReader reader = new XlsTeacherReader();
            List<TeacherDAO> teacherDAOList = reader.parseAll(sheet);
            teacherRepository.save(teacherDAOList);
            repository.save(dao);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MessageException("读取文件错误");
        }
        return Response.success(dao);
    }

    @RequestMapping(path = "backup/teacher", method = RequestMethod.POST)
    public Response<String> downloadTeacher() {
        XlsTeacherWriter writer = new XlsTeacherWriter();
        XSSFWorkbook wb = new XSSFWorkbook();
        writer.writeAll(wb.createSheet(), teacherRepository.findAll());
        String fileName = String.format("teachers_%d.xlsx", (new Date()).getTime());
        File saveFile = new File(Constants.getDownloadPath(), fileName);
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(saveFile);
            wb.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new MessageException("文件不存在");
        } catch (IOException e) {
            e.printStackTrace();
            throw new MessageException("文件写入错误");
        }
        return Response.success(fileName);
    }

    private XlsFileDAO filterFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new MessageException("文件为空");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("文件的后缀名为：" + suffixName);
        String lowerCaseSuffix = suffixName.toLowerCase();
        if (!lowerCaseSuffix.equals(".xls") && !lowerCaseSuffix.equals(".xlsx")) {
            throw new MessageException("上传文件为非 xls 文件");
        }
        // 文件存储路径
        String filePath = Constants.getClassRootPath();
        File resFile = new File(filePath, fileName);
        if (!resFile.getParentFile().exists()) {
            resFile.getParentFile().mkdirs();
        }
        try {
            file.transferTo(resFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MessageException("文件读写错误");
        }
        XlsFileDAO dao = new XlsFileDAO();
        dao.setFilePath(resFile.getAbsolutePath());
        logger.info("save file to: " + resFile.getAbsolutePath());
        // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            InputStream inputStream = new FileInputStream(resFile);
            int length = -1;
            while ((length = inputStream.read(buffer, 0, 1024)) != -1) {
                messageDigest.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, messageDigest.digest());
            dao.setFileMd5(bigInt.toString(16));
            logger.info("获取的文件的 md5：" + dao.getFileMd5());

            Specifications<XlsFileDAO> specifications = Specifications
                    .where(XlsFilterHelper.filterByMd5(dao.getFileMd5()));

            List<XlsFileDAO> xlsFileDAOList = repository.findAll(specifications);
            if (xlsFileDAOList != null && !xlsFileDAOList.isEmpty()) {
                throw new MessageException("文件已经被添加过");
            }
            return dao;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new MessageException("Md5 读取错误");
        } catch (IOException e) {
            e.printStackTrace();
            throw new MessageException("转换 inputstream 错误");
        }
    }
}
