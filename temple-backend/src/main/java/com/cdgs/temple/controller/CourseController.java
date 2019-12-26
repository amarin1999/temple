package com.cdgs.temple.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.cdgs.temple.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cdgs.temple.entity.CourseEntity;
import com.cdgs.temple.service.ApprovalCoursesService;
import com.cdgs.temple.service.CourseScheduleService;
import com.cdgs.temple.service.CourseService;
import com.cdgs.temple.service.CourseTeacherService;
import com.cdgs.temple.service.MemberService;
import com.cdgs.temple.service.MembersHasCourseService;
import com.cdgs.temple.service.SensationService;
import com.cdgs.temple.service.SpecialApproveService;
import com.cdgs.temple.util.ResponseDto;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/courses")
public class CourseController {

    private MemberService memberService;
    private CourseService courseService;
    private MembersHasCourseService membersHasCourseService;
    private CourseScheduleService courseScheduleService;
    private CourseTeacherService courseTeacherservice;
    private SpecialApproveService specialApproveService;
    private SensationService sensationService;
    private ApprovalCoursesService approvalCoursesService; 

    @Autowired
    public CourseController(CourseService courseService, MemberService memberService,
            MembersHasCourseService membersHasCourseService, CourseScheduleService courseScheduleService,
            CourseTeacherService courseTeacherService, SpecialApproveService specialApproveService, 
            SensationService sensationService, ApprovalCoursesService approvalCoursesService) {
        this.specialApproveService = specialApproveService;
        this.courseService = courseService;
        this.memberService = memberService;
        this.membersHasCourseService = membersHasCourseService;
        this.courseScheduleService = courseScheduleService;
        this.courseTeacherservice = courseTeacherService;
        this.sensationService = sensationService;
        this.approvalCoursesService = approvalCoursesService;
    }

    // @GetMapping(path = "")
    // @PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
    // public ResponseEntity<List<CourseEntity>> getAllUsers() {
    // List<CourseEntity> user = courseRepository.findAll();
    // return new ResponseEntity<List<CourseEntity>>(user, HttpStatus.OK);
    // }

    @GetMapping(path = "/allmembers/{id}")
    @PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
    public ResponseEntity<ResponseDto<MembersHasCourseDto>> getAllUsers(@PathVariable(value = "id") Long courseId) {
        // @GetMapping(path = "/allmembers")
        // @PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
        // public ResponseEntity<ResponseDto<MembersHasCourseDto>>
        // getAllUsers(@RequestParam("courseId") Long courseId) {
        ResponseDto<MembersHasCourseDto> res = new ResponseDto<>();
        try {
            List<MembersHasCourseDto> memberHasCourse = membersHasCourseService.getMembersByCourse(courseId);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            
            res.setData(memberHasCourse);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/user")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<ResponseDto<CourseDto>> GetCoursesUser(@RequestParam("status") String status) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> dto = new ArrayList<>();
        MemberDto member = memberService.getCurrentMember();
        try { 
        		 if(status.equals("0")) {
        	
        			  dto = courseService.getCoursesUserRegister(member.getId()); 
        		 }else {
        			 dto = courseService.getCoursesUser(member.getId(),status);
        		 }
        		

            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * GetCourses
     * this function for get courses by admin and monk
     * 
     * */
    
    @GetMapping(path = "")
    @PreAuthorize("hasRole('admin') or hasRole('monk')")
    public ResponseEntity<ResponseDto<CourseDto>> GetCourses() {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> dto = new ArrayList<>();
        MemberDto member = memberService.getCurrentMember();

        try { 
        	 if(member.getRoleName().equals("monk")){
        		 dto = courseService.getCoursesMonk(member.getId());	  
	         }
	        else {
	            	dto = courseService.getCourses();
	            }
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println("catch");
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/count")
    @PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
    public ResponseEntity<ResponseDto<ResponseCountDto>> countCourses(@RequestParam("status") String status) {
        ResponseDto<ResponseCountDto> res = new ResponseDto<>();
        List<ResponseCountDto> listDto = new ArrayList<>();
        ResponseCountDto dto = new ResponseCountDto();
        int count = 0;
        MemberDto member = memberService.getCurrentMember();
        /*
         * */
        try {
            if (member.getRoleName().equals("user")) {
        		if(status.equals("0")) {
        			count = courseService.countUserRegisterCourses(member.getId());
        		}else if(status.equals("2") || status.equals("1")) {
        			count = courseService.countUserCourses(member.getId(),status);
        		}
        		
        	}else if(member.getRoleName().equals("monk")){
        		count = courseService.countMonkCourses(member.getId());
        	}
        	else{
        		count = courseService.countCourses();
        		System.out.println("admin" + count);
        	}
        		
	            //count = courseService.countCourses();
	           
	            dto.setTotalRecord(count);
	            listDto.add(dto);
	            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
	            res.setData(listDto);
	            res.setCode(201);
	            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/approve")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<ApprovalCoursesDto>> TeacherGetCoursesApproval(@RequestParam("offset") int offset,
            @RequestParam("limit") int limit, @RequestParam("query") String query) {
        ResponseDto<ApprovalCoursesDto> res = new ResponseDto<>();
        List<ApprovalCoursesDto> dto;
        MemberDto member = memberService.getCurrentMember();
       
        try {
            System.out.println(offset);
            System.out.println(limit);
            // เปลี่ยนไปใช้ ApprovalCourses จากเดิม Course (TeacherGetCoursesApproval) เพิ่มจำนวนนักเรียนในแต่ละคอร์ส
            dto = approvalCoursesService.getApprovalCourses(query, member.getId(), limit, offset);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping(value = "/approve/outTime")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<CourseDto>> TeacherGetCoursesOutTime(@RequestParam("offset") int offset,
            @RequestParam("limit") int limit, @RequestParam("query") String query) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> dto;
        MemberDto member = memberService.getCurrentMember();
       
        try {
            System.out.println(offset);
            System.out.println(limit);
            // เปลี่ยนไปใช้ ApprovalCourses จากเดิม Course (TeacherGetCoursesApproval) เพิ่มจำนวนนักเรียนในแต่ละคอร์ส
            dto = courseService.TeacherGetCoursesApprovalOutTime(member.getId(), offset, limit, query);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/approve/count")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<ResponseCountDto>> CountTeacherCoursesApproval() {
        ResponseDto<ResponseCountDto> res = new ResponseDto<>();
        List<ResponseCountDto> listDto = new ArrayList<>();
        ResponseCountDto dto = new ResponseCountDto();
        int count;
        MemberDto member = memberService.getCurrentMember();
        try {
            count = courseService.CountTeacherCoursesApproval(member.getId());
            dto.setTotalRecord(count);
            listDto.add(dto);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(listDto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/approve/outTime/count")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<ResponseCountDto>> CountTeacherCoursesApprovalOutTime() {
        ResponseDto<ResponseCountDto> res = new ResponseDto<>();
        List<ResponseCountDto> listDto = new ArrayList<>();
        ResponseCountDto dto = new ResponseCountDto();
        int count;
        MemberDto member = memberService.getCurrentMember();
        try {
            count = courseService.CountTeacherCoursesApprovalOutTime(member.getId());
            dto.setTotalRecord(count);
            listDto.add(dto);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(listDto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * getCoursesById
     * this function for get courses by ID 
     * params: id of row
     * */
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
    public ResponseEntity<ResponseDto<CourseDto>> getCoursesById(@PathVariable("id") Long id) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> dto = new ArrayList<>();
        MemberDto member = memberService.getCurrentMember();

        try {
            if (member.getRoleName().equals("user")) {
                dto.add(courseService.getCourseUser(member.getId(), id));
                System.out.println(dto);

            } else {
                dto.add(courseService.getCourse(id));
                System.out.println(id);
                System.out.println(member.getRoleName());

            }

            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(path = "/outTime")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<ResponseDto<CourseDto>> GetCoursesOutTime(@RequestParam("courseOutTimeType") String courseOutTimeType) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> listCourseDto;

        MemberDto member = memberService.getCurrentMember();
        try {
        	if(courseOutTimeType.equals("Specialapprove")) {
        		listCourseDto = courseService.getCoursesSpecialApproveOutTime(member.getId());
        	}else if(courseOutTimeType.equals("MemberHasCourse")) {
        		listCourseDto = courseService.getCoursesMemberHasCourseOutTime(member.getId());
        	}else if(courseOutTimeType.equals("MemberHasOutTime")) {
        		listCourseDto = courseService.getCoursesOutTimeByMemberId(member.getId());
        	}else if(courseOutTimeType.equals("MemberHasToStudy")){
        		listCourseDto = courseService.getCoursesMemberToStudy(member.getId());
        	}else {
        		listCourseDto = courseService.getCoursesOutTime();
        	}
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(listCourseDto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/getPassCourse/{id}")
    public ResponseEntity<ResponseDto<Long>> getMemberPassCourse(@PathVariable("id") Long id) {
    	ResponseDto<Long> res = new ResponseDto<>();
    	List<Long> dto = new ArrayList<>();
        Long passCourse = null;
        	passCourse = membersHasCourseService.countForPassCourses(id);
        	dto.add(passCourse);
        	res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    
    /**
     * createCourse
     * this function is create new course by admin
     * 
     * */
    @PostMapping(path = "")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseDto<CourseDto>> createCourse(@Valid @RequestBody CourseDto body) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> courses = new ArrayList<>();
        CourseDto course;
        CourseScheduleDto courseSchedule = new CourseScheduleDto();
        CourseTeacherDto courseTeacher = new CourseTeacherDto();
        Date dateSt = body.getDate().get(0);
    	Date dateEnd = body.getDate().get(1);
        try {
            course = courseService.createCourse(body);
            courseSchedule.setCourseId(course.getId());
            //ตรวจสอบกรณีวันที่เท่ากัน / 1 วัน
            if(dateSt.compareTo(dateEnd) == 0){
            	courseSchedule.setCourseScheduleDate(body.getDate().get(0));
                courseScheduleService.createCourseSchedule(courseSchedule);
            }else {
            	for (Date date : body.getDate()) {
                System.out.println("***date = " + date);
                courseSchedule.setCourseScheduleDate(date);
                courseScheduleService.createCourseSchedule(courseSchedule);
            	}
            }
            
            courseTeacher.setCourseId(course.getId());
            for (Long tId : body.getTeacher()) {
                courseTeacher.setMemberId(tId);
                courseTeacher = courseTeacherservice.createCourseTeacher(courseTeacher);

            }

            courses.add(course);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(courses);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/outTime")
	  public ResponseEntity<ResponseDto<CourseDto>> AssignCoursesOutTime(@Valid @RequestBody SpecialApproveDto body){
	  	ResponseDto<CourseDto> res = new ResponseDto<>();
	  	CourseDto courseDto = new CourseDto();
	  	CourseDto courseOutTimeDto = new CourseDto();
	  	CourseTeacherDto courseTeacherDto = new CourseTeacherDto();
	  	SpecialApproveDto specialApproveDto = new SpecialApproveDto();
	  	List<CourseDto> listDto = new ArrayList<>();
	  	MemberDto member = memberService.getCurrentMember();
	  	try {
	  		//create CourseOutTime
	  		courseDto = courseService.getCourseById(body.getCourseId());
	  		courseDto.setId(null);
	  		courseDto.setNo(body.getCourseId());
	  		courseDto.setStatus("0");
	  		courseDto.setStDate(body.getStDate());
	  		courseDto.setEndDate(body.getEndDate());
	  		courseOutTimeDto = courseService.createCourse(courseDto);
	  		
	  		courseTeacherDto.setCourseId(courseOutTimeDto.getId());
          for (Long tId : courseDto.getTeacher()) {
          	courseTeacherDto.setMemberId(tId);
          	courseTeacherDto = courseTeacherservice.createCourseTeacher(courseTeacherDto);
          }
	  		
	  		body.setCourseId(courseOutTimeDto.getId());
	  		body.setStatus("4");
	  		body.setMemberId(member.getId());
	  		specialApproveDto = specialApproveService.create(body);
	  		
	  		listDto.add(courseOutTimeDto);
	        res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
	        res.setData(listDto);
	        res.setCode(200);
		    return new ResponseEntity<>(res, HttpStatus.OK);
	    } catch (Exception e) {
	        res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
	        res.setErrorMessage(e.getMessage());
	        res.setCode(400);
		    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	    }
	}
    
    /**
     * putCourse
     * this function for update course by admin.
     * params: id of row and body: CourseDto
     * */
    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseDto<CourseDto>> putCourse(@PathVariable("id") Long id,
            @Valid @RequestBody CourseDto body) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> courses = new ArrayList<>();
        CourseTeacherDto courseTeacher = new CourseTeacherDto();
        CourseDto course;
        CourseScheduleDto courseSchedule = new CourseScheduleDto();
        try {
            System.out.println("print");
            course = courseService.updateCourse(id, body);
            System.out.println("body st = " + body.getStDate());
            System.out.println("body condition = " + body.getConditionMin());
            
            System.out.println("body end = " + body.getEndDate());
            System.out.println("course end = " + course.getStDate());
            System.out.println("course end = " + course.getEndDate());
            System.out.println(body);
            if (body != null) {
                courses.add(body);
            }
            /*ตรวจสอบเพิ่มกรณีไม่มีข้อมูลใน schedule*/
            if(courseScheduleService.getCourseId(id) == null) {
                courseSchedule.setCourseId(course.getId());
                for (Date date : body.getDate()) {
                    System.out.println("***date = " + date);
                    courseSchedule.setCourseScheduleDate(date);
                    courseScheduleService.createCourseSchedule(courseSchedule);
                }
        	}
            
            courseTeacher.setCourseId(course.getId());
            for (Long tId : body.getTeacher()) {
                courseTeacher.setMemberId(tId);
                courseTeacher = courseTeacherservice.createCourseTeacher(courseTeacher);
            }
            
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(courses);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseDto<CourseEntity>> deleteCourse(@PathVariable("id") long id) {
        ResponseDto<CourseEntity> res = new ResponseDto<>();
        if (courseService.deleteCourse(id)) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setCode(204);
            return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
        } else {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/register")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<ResponseDto<MembersHasCourseDto>> createMemberHasCourse(
            @Valid @RequestBody MembersHasCourseDto body) {
        ResponseDto<MembersHasCourseDto> res = new ResponseDto<>();
        List<MembersHasCourseDto> courses = new ArrayList<>();
        MembersHasCourseDto memberHasCourse;
        MemberDto member = memberService.getCurrentMember();
        SensationDto tempSensation = new SensationDto();
        SensationDto sensation = new SensationDto();
        System.out.println("Detail" + body.getTransportationId());
       
        try {
            if (courseService.getCoursesUserByCourseId(member.getId(), body.getCourseId()) == 0) {
            	tempSensation.setId(body.getSenseId());
            	tempSensation.setExpected(body.getExpected());
            	tempSensation.setExperience(body.getExperience());
            	sensation = sensationService.createSensation(tempSensation);

            	body.setMemberId(member.getId());
            	body.setSenseId(sensation.getId());
                body.setStatus('2'); 
                System.out.println(body.getStatus());
                memberHasCourse = courseService.assignCourse(body);
                if (memberHasCourse == null) {
                	throw new Exception("เงื่อนไขการสมัครไม่ถูกต้อง");
                }
                
                courses.add(memberHasCourse);
                res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
                res.setData(courses);
                res.setCode(200);
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
                res.setErrorMessage("Duplicate register");
                res.setCode(200);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "/schedule")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<ResponseDto<CourseScheduleDto>> postCourseSchedule(
            @Valid @RequestBody CourseScheduleDto courseSchedule) {
        ResponseDto<CourseScheduleDto> res = new ResponseDto<>();
        List<CourseScheduleDto> courseSchedules = new ArrayList<>();
        CourseScheduleDto dto;
        try {
            dto = courseScheduleService.createCourseSchedule(courseSchedule);
            courseSchedules.add(dto);
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(courseSchedules);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400	);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/schedule")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<ResponseDto<CourseScheduleDto>> getCourseScheduleByUser() {
        ResponseDto<CourseScheduleDto> res = new ResponseDto<>();
        List<CourseScheduleDto> dto;
        MemberDto member = memberService.getCurrentMember();
        try {
            dto = courseScheduleService.getCourseScheduleByUser(member.getId());
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            System.out.println(dto.toString());
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/teacher_schedule")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<CourseScheduleDto>> getCourseScheduleByMonk() {
        ResponseDto<CourseScheduleDto> res = new ResponseDto<>();
        List<CourseScheduleDto> dto;
        MemberDto member = memberService.getCurrentMember();
        try {
            dto = courseScheduleService.getCourseScheduleByMonk(member.getId());
            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "/deleteCourse/{id}")
    @PreAuthorize("hasRole('monk')")
    public ResponseEntity<ResponseDto<CourseDto>> deleteCourse(@PathVariable("id") Long id) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        try {
            if (courseService.deleteCourse(id)) {
            	res.setResult("Success");
            	res.setCode(200);
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
            	res.setResult("Fail");
                res.setCode(400);
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
                
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/history/{id}")
    @PreAuthorize("hasRole('admin') or hasRole('monk') or hasRole('user')")
    public ResponseEntity<ResponseDto<CourseDto>> getHistoryCourseLearn(@PathVariable("id") Long id) {
        ResponseDto<CourseDto> res = new ResponseDto<>();
        List<CourseDto> dto = new ArrayList<>();
        MemberDto member = memberService.getCurrentMember();

        try {
            if (member.getRoleName().equals("user")) {
                // dto.add(courseService.getHistory(member.getId()));
                dto = courseService.getHistory(member.getId());
            } else {
                // dto.add(courseService.getHistory(id));
                dto = courseService.getHistory(id);
            }

            res.setResult(ResponseDto.RESPONSE_RESULT.Success.getRes());
            res.setData(dto);
            res.setCode(200);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setResult(ResponseDto.RESPONSE_RESULT.Fail.getRes());
            res.setErrorMessage(e.getMessage());
            res.setCode(400);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
    


}
