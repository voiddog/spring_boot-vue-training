package org.voiddog.spring.test.teacher;

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
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherRepository repository;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Response<TeacherDAO> addTeacher(@RequestBody AddTeacherRequest request) {
        TeacherDAO dao = new TeacherDAO();
        BeanUtils.copyProperties(request, dao);
        dao = repository.save(dao);
        return Response.success(dao);
    }

    @RequestMapping(path = "/remove")
    public Response<Boolean> removeTeacher(@RequestParam Long teacherId) {
        repository.delete(teacherId);
        return Response.success(true);
    }

    @RequestMapping("/filter")
    public Response<Iterable<TeacherDAO>> filter(@RequestBody TeacherFilterRequest request) {
        if (request.getId() != null) {
            // have id
            List<Long> idList = new ArrayList<>();
            idList.add(request.getId());
            return Response.success(repository.findAll(idList));
        }
        Specifications<TeacherDAO> specifications = Specifications
                .where(TeacherFilterHelper.filterByAge(request.getMinAge(), request.getMaxAge()))
                .and(TeacherFilterHelper.filterByName(request.getName()))
                .and(TeacherFilterHelper.filterByJob(request.getJob()))
                .and(TeacherFilterHelper.filterByOrganization(request.getOrganization()))
                .and(TeacherFilterHelper.filterByPhone(request.getPhone()))
                .and(TeacherFilterHelper.filterByType(request.getType()))
                .and(TeacherFilterHelper.filterByLevelType(request.getLevelType()))
                .and(TeacherFilterHelper.filterByGender(request.getGender()));

        return Response.success(repository.findAll(specifications,
                new PageRequest(request.getPageIndex(), request.getPageSize(), Sort.Direction.ASC, "id"))
        );
    }

    @RequestMapping("/update")
    public Response<TeacherDAO> updateTeacher(@RequestBody UpdateTeacherRequest request) {
        if (request.getId() == null){
            throw new MessageException("教师不存在");
        }
        TeacherDAO findDao = repository.findOne(request.getId());
        if (findDao == null){
            throw new MessageException("教师不存在");
        }
        DogObjectUtils.copyPropIfNotNull(request, findDao);
        return Response.success(repository.save(findDao));
    }

    @RequestMapping(path = "/all")
    public Response<Iterable<TeacherDAO>> all() {
        return Response.success(repository.findAll());
    }
}
