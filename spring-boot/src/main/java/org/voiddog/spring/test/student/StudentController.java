package org.voiddog.spring.test.student;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.web.bind.annotation.*;
import org.voiddog.spring.test.DogObjectUtils;
import org.voiddog.spring.test.model.MessageException;
import org.voiddog.spring.test.model.Response;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/add")
    public Response<StudentDAO> add(@RequestBody AddStudentRequest request){
        StudentDAO dao = new StudentDAO();
        BeanUtils.copyProperties(request, dao);
        return Response.success(studentRepository.save(dao));
    }

    @GetMapping("/all")
    public Response<Iterable<StudentDAO>> all(){
        return Response.success(studentRepository.findAll());
    }

    @RequestMapping("/filter")
    public Response<Iterable<StudentDAO>> filter(@RequestBody StudentFilterRequest request){
        if (request.getId() != null){
            // have id
            List<Long> idList = new ArrayList<>();
            idList.add(request.getId());
            return Response.success(studentRepository.findAll(idList));
        }
        Specifications<StudentDAO> specifications = Specifications
                .where(StudentFilterHelper.filterByAge(request.getMinAge(), request.getMaxAge()))
                .and(StudentFilterHelper.filterByName(request.getName()))
                .and(StudentFilterHelper.filterByJob(request.getJob()))
                .and(StudentFilterHelper.filterByOrganization(request.getOrganization()))
                .and(StudentFilterHelper.filterByPhone(request.getPhone()))
                .and(StudentFilterHelper.filterByGender(request.getGender()));

        return Response.success(studentRepository.findAll(specifications,
                new PageRequest(request.getPageIndex(), request.getPageSize(), Sort.Direction.ASC, "id"))
        );
    }

    @RequestMapping("/update")
    public Response<StudentDAO> updateTeacher(@RequestBody UpdateStudentRequest request) {
        if (request.getId() == null){
            throw new MessageException("学员不存在");
        }
        StudentDAO findDao = studentRepository.findOne(request.getId());
        if (findDao == null){
            throw new MessageException("学员不存在");
        }
        DogObjectUtils.copyPropIfNotNull(request, findDao);
        return Response.success(studentRepository.save(findDao));
    }
}
