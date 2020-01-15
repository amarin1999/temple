package com.cdgs.temple.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdgs.temple.dto.CourseDto;
import com.cdgs.temple.dto.CourseScheduleDto;
import com.cdgs.temple.dto.CourseTeacherDto;
import com.cdgs.temple.dto.MemberDto;
import com.cdgs.temple.dto.MembersHasCourseDto;
import com.cdgs.temple.dto.TransportationDto;
import com.cdgs.temple.entity.CourseEntity;
import com.cdgs.temple.entity.CourseScheduleEntity;
import com.cdgs.temple.entity.CourseTeacherEntity;
import com.cdgs.temple.entity.MembersHasCourseEntity;
import com.cdgs.temple.entity.TempCourseEntity;
import com.cdgs.temple.entity.TransportationEntity;
import com.cdgs.temple.repository.CourseRepository;
import com.cdgs.temple.repository.CourseScheduleRepository;
import com.cdgs.temple.repository.CourseTeacherRepository;
import com.cdgs.temple.repository.GraduatedRepository;
import com.cdgs.temple.repository.LocationRepository;
import com.cdgs.temple.repository.MembersHasCourseRepository;
import com.cdgs.temple.repository.SpecialApproveRepository;
import com.cdgs.temple.repository.TempCourseRepository;
import com.cdgs.temple.service.CourseScheduleService;
import com.cdgs.temple.service.CourseService;
import com.cdgs.temple.service.MemberService;
import com.cdgs.temple.service.TransportationService;

@Service
public class CourseServiceImpl implements CourseService {

	private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

	private CourseRepository courseRepository;

	private TempCourseRepository tempCourseRepository;

	private MembersHasCourseRepository membersHasCourseRepository;

	private CourseScheduleRepository courseScheduleRepository;

	private MemberService memberService;

	private SpecialApproveRepository specialApproveRepository;

	private GraduatedRepository graduatedRepository;

	private LocationRepository locationRepository;

	private CourseTeacherRepository courseTeacherRepository;

	private CourseScheduleService courseScheduleService;

	private TransportationService transportationService;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository, MembersHasCourseRepository membersHasCourseRepository,
			CourseScheduleRepository courseScheduleRepository, MemberService memberService,
			TempCourseRepository tempCourseRepository, SpecialApproveRepository specialApproveRepository,
			GraduatedRepository graduatedRepository, LocationRepository locationRepository,
			CourseTeacherRepository courseTeacherRepository, CourseScheduleService courseScheduleService,
			TransportationService transportationService) {

		this.courseRepository = courseRepository;
		this.membersHasCourseRepository = membersHasCourseRepository;
		this.courseScheduleRepository = courseScheduleRepository;
		this.memberService = memberService;
		this.tempCourseRepository = tempCourseRepository;
		this.specialApproveRepository = specialApproveRepository;
		this.graduatedRepository = graduatedRepository;
		this.locationRepository = locationRepository;
		this.courseTeacherRepository = courseTeacherRepository;
		this.courseScheduleService = courseScheduleService;
		this.transportationService = transportationService;

	}

	@Override
	public List<CourseDto> getHistory(Long memberId) {
		final char PASS_STATUS = '1';
		List<MembersHasCourseEntity> memberHasCourses = membersHasCourseRepository.findAllByMemberIdAndStatus(memberId,
				PASS_STATUS);
		List<CourseDto> coursesDto;
		List<CourseEntity> courses = new ArrayList<>();
		for (MembersHasCourseEntity memberHasCourse : memberHasCourses) {
			courses.add(memberHasCourse.getCourse());
		}
		coursesDto = mapListEntityToDto(courses);
		// if(coursesDto != null){
		return coursesDto;
		// }else{
		// throw new RuntimeException("สมาชิกยังไม่เคยผ่านหลักสูคร");
		// }

	}

	@Override
	public List<CourseDto> getCoursesUserRegister(Long memberId) {
		List<TempCourseEntity> entity = tempCourseRepository.findCoursesUserRegister(memberId);
		// System.out.println(entity.size());
		return mapListTempEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCoursesUser(Long memberId, String status) {
		String enable = "";
		if (status.equals("1")) {
			enable = "*";
		} else {
			enable = "1";
		}
		System.out.println(status);
		System.out.println(enable);
		List<TempCourseEntity> entity = tempCourseRepository.findCoursesUser(memberId, status, enable);
		return mapListTempEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCoursesMonk(Long memberId) {
		List<CourseEntity> entities = courseRepository.findCoursesMonk(memberId);
		return mapListEntityToDto(entities);
	}

	@Override
	public List<CourseDto> getCourses() {
		List<CourseEntity> entities = courseRepository.findAllCourseEntity();
		return mapListEntityToDto(entities);
	}

	/*
	 * createCourse this function for create course by admin.
	 */
	@Override
	public CourseDto createCourse(CourseDto body) {
		MemberDto member = memberService.getCurrentMember();
		body.setMemberId(member.getId());
		CourseEntity entity;
		try {
			entity = courseRepository.save(convDtoToEntity(body));
			return mapEntityToDto(entity);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("createCourse>>> " + e.getMessage());
			return null;
		}
	}

	@Override
	public Boolean deleteCourse(long id) {
		try {
			courseRepository.deleteCourse(id);
			specialApproveRepository.cancelApprove(id);
			graduatedRepository.cancelGraduted(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public CourseDto getCourse(Long id) {
		try {
			CourseEntity entity = courseRepository.findById(id).get();
			return mapEntityEditToDto(entity);
		} catch (Exception e) {
			log.error("getCourse >>>> " + e.getMessage());
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public CourseDto getCourseMonk(Long id) {
		CourseEntity entity = courseRepository.findById(id).get();
		return mapEntityEditToDto(entity);

	}

	@Override
	public Integer getCoursesUserByCourseId(Long memberId, Long courseId) {
		return courseRepository.findCoursesUserByCourseId(memberId, courseId);
	}

	@Override
	public CourseDto getCourseUser(Long memberId, Long courseId) {
		TempCourseEntity entity = tempCourseRepository.findCourseUserById(memberId, courseId);
		return mapTempEntityToDto(entity);

	}

	@Override
	public MembersHasCourseDto assignCourse(MembersHasCourseDto body) {
		MembersHasCourseEntity entity = mapDtoToEntity(body);
		long count = membersHasCourseRepository.CountForPassCourse(body.getMemberId());
		CourseEntity course = courseRepository.findById(body.getCourseId()).get();
		System.out.println("member id " + body.getMemberId());
		System.out.println("course " + body.getCourseId());
		System.out.println("count " + count);
		System.out.println("condition " + course.getCourseConditionMin());
		try {
			if (count >= course.getCourseConditionMin()) {
				return mapEntityToDto(membersHasCourseRepository.save(entity));
			} else {
				throw new Exception("เงื่อนไขการสมัครไม่ถูกต้อง aa");
			}
		} catch (Exception e) {
			log.error("assignCourse>>> " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<CourseDto> TeacherGetCoursesApproval(Long memberId) {

		List<CourseEntity> course = courseRepository.fetchCoursesTeacherApproval(memberId);
		if (course != null) {
			return mapListEntityToDto(course);
		}
		return null;
	}

	@Override
	public Integer CountTeacherCoursesApproval(Long memberId) {
		Integer courses = courseRepository.countCoursesTeacherApprovalAll(memberId);
		if (courses != null) {
			return courses;
		}
		return null;
	}

	@Override
	public Integer countCourses() {
		return courseRepository.countAllBycourseEnableIsTrue();
	}

	@Override
	public Integer countUserRegisterCourses(Long memberId) {
		return courseRepository.countUserAllBycourseEnableIsTrue(memberId);
	}

	@Override
	public Integer countUserCourses(Long memberId, String status) {
		String enable = "";
		if (status.equals("1")) {
			enable = "*";
		} else {
			enable = "1";
		}
		return courseRepository.countCourseByEnableIsTrueAndStatus(memberId, status, enable);
	}

	@Override
	public Integer countMonkCourses(Long memberId) {
		return courseRepository.countCoursesTeacherAll(memberId);
	}

	@Override
	public List<CourseDto> getCoursesOutTime() {
		List<CourseEntity> entity = courseRepository.getAllCourseOutTime();
		return mapListEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCoursesSpecialApproveOutTime(Long memberId) {
		List<CourseEntity> entity = courseRepository.getCourseSpecialApprove(memberId);
		return mapListEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCoursesMemberHasCourseOutTime(Long memberId) {
		List<CourseEntity> entity = courseRepository.getCourseMemberHasCourse(memberId);
		return mapListEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCoursesOutTimeByMemberId(Long memberId) {
		List<CourseEntity> entity = courseRepository.getCourseOutTimeByMemberId(memberId);
		return mapListEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCoursesMemberToStudy(Long memberId) {
		List<CourseEntity> entity = courseRepository.getCoursesMemberToStudy(memberId);
		return mapListEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCourseOutTimeFromSpecialApproveIdByMemberId(Long memberId) {
		List<CourseEntity> entity = courseRepository.getCourseSpecialApproveByMemberId(memberId);
		return mapListEntityToDto(entity);
	}

	@Override
	public List<CourseDto> getCourseOutTimeFromSpecialApproveSuccess(Long memberId) {
		List<CourseEntity> entity = courseRepository.getCourseSpecialApproveSuccess(memberId);
		return mapListEntityToDto(entity);
	}

	@Override
	public List<CourseDto> TeacherGetCoursesApprovalOutTime(Long memberId, int offset, int limit, String query) {

		List<CourseEntity> course = courseRepository.fetchCoursesTeacherApprovalOutTime(memberId, offset, limit, query);
		if (course != null) {
			return mapListEntityToDto(course);
		}
		return null;
	}

	@Override
	public Integer CountTeacherCoursesApprovalOutTime(Long memberId) {
		Integer courses = courseRepository.countCoursesTeacherApprovalAllOutTime(memberId);
		if (courses != null) {
			return courses;
		}
		return null;
	}

	@Override
	public void updateCourseToEnable(Long id) {
		courseRepository.updateCourseToEnable(id);
	}

	private List<CourseDto> mapListEntityToDto(List<CourseEntity> entities) {
		List<CourseDto> listDto = new ArrayList<>();
		if (!entities.isEmpty()) {
			for (CourseEntity entitiy : entities) {
				listDto.add(mapEntityToDto(entitiy));
			}
		}
		return listDto;

	}

	@Override
	public CourseDto getCourseById(Long courseId) {
		CourseEntity entity = courseRepository.findById(courseId).get();
		return mapEntityToDto(entity);
	}

	private CourseDto mapEntityToDto(CourseEntity entity) {
		CourseDto dto = new CourseDto();
		CourseTeacherDto teacherDto = new CourseTeacherDto();
		TransportationDto transportationDto = new TransportationDto();
		List<Long> teacherList = new ArrayList<>();
		try {
			if (entity != null) {
				dto.setId(entity.getCourseId());
				dto.setNo(entity.getCourseNo());
				dto.setName(entity.getCourseName());
				dto.setDetail(entity.getCourseDetail());
				dto.setStDate(entity.getCourseStDate());
				dto.setEndDate(entity.getCourseEndDate());
				dto.setConditionMin(entity.getCourseConditionMin());
				dto.setStatus(entity.getCourseStatus());
				dto.setCreateDate(entity.getCourseCreateDate());
				dto.setLastUpdate(entity.getCourseLastUpdate());
				if (entity.getLocationName() != null) {
					dto.setLocationName(entity.getLocationName());
				}
				if (entity.getLocationId() != null) {
					dto.setLocationId(entity.getLocationId().getLocationId());
					dto.setLocationName(entity.getLocationId().getLocationName());
				}
				if (entity.getTransportationId() != null) {
					transportationDto = transportationService.getTransportationByCourseId(entity.getCourseId());
					dto.setTransportation(transportationDto);
				}
				if (entity.getCreateBy() != null) {
					dto.setMemberId(entity.getCreateBy().getMemberId());
					dto.setMemberFname(entity.getCreateBy().getMemberFname());
					dto.setMemberLname(entity.getCreateBy().getMemberLname());
				}
				if (entity.getCourseTeacher() != null) {
					for (CourseTeacherEntity teacher : entity.getCourseTeacher()) {
						teacherDto.setMemberId(teacher.getMemberId());
						teacherList.add(teacherDto.getMemberId());
					}
					dto.setTeacher(teacherList);
				}
			}
			return dto;
		} catch (Exception e) {
			return null;
		}

	}

	private List<CourseDto> mapListTempEntityToDto(List<TempCourseEntity> entities) {
		List<CourseDto> listDto = new ArrayList<>();
		if (!entities.isEmpty()) {
			for (TempCourseEntity entitiy : entities) {
				listDto.add(mapTempEntityToDto(entitiy));
			}
		}
		return listDto;

	}

	private CourseDto mapTempEntityToDto(TempCourseEntity entity) {
		CourseDto dto = new CourseDto();
		if (entity != null) {
			dto.setId(entity.getCourseId());
			dto.setNo(entity.getCourseNo());
			dto.setName(entity.getCourseName());
			dto.setDetail(entity.getCourseDetail());
			dto.setStDate(entity.getStDate());
			dto.setEndDate(entity.getEndDate());
			dto.setConditionMin(entity.getCourseConditionMin());
			dto.setCreateDate(entity.getCourseCreateDate());
			dto.setLastUpdate(entity.getCourseLastUpdate());
			dto.setLocationId(entity.getLocationId().getLocationId());
			dto.setLocationName(entity.getLocationId().getLocationName());
			dto.setMemberId(entity.getCreateBy().getMemberId());
			dto.setMemberFname(entity.getCreateBy().getMemberFname());
			dto.setMemberLname(entity.getCreateBy().getMemberLname());
			dto.setStatus(entity.getStatusText());
			dto.setSaStatus(entity.getSaStatus());
			dto.setMhcStatus(entity.getMhcStatus());
			dto.setCanRegister(entity.getCanRegister());
//			dto.setTransportTempleId(entity.getTransportTempleId());
		} else {
			dto = null;
		}
		return dto;
	}

	@Transactional
	public CourseDto updateCourse(Long id, CourseDto courseNew) {
		CourseEntity courseOld = courseRepository.findById(id).get();
		CourseEntity entity;
		try {
			System.out.println("st1 = " + courseNew.getStDate());

			// set update name, detail, location
			courseOld.setCourseName(courseNew.getName());
			System.out.println("name = " + courseNew.getName());
			courseOld.setCourseDetail(courseNew.getDetail());
			System.out.println("detail = " + courseNew.getDetail());
			courseOld.setLocationId(locationRepository.findById(courseNew.getLocationId()).get());
			courseOld.setCourseStDate(courseNew.getStDate());
			System.out.println("st = " + courseNew.getStDate());
			System.out.println("stOld = " + courseOld.getCourseStDate());
			courseOld.setCourseConditionMin(courseNew.getConditionMin());
			courseOld.setCourseEndDate(courseNew.getEndDate());
			System.out.println("end = " + courseNew.getEndDate());
			System.out.println("endOld = " + courseOld.getCourseEndDate());
			String datetest = "2019-12-12";
			Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(datetest);
			System.out.println("datetest =" + datetest);
			courseOld.setCourseLastUpdate(new Date());
			courseOld.setCourseLocationId(courseNew.getLocationId());
			entity = courseRepository.save(courseOld);
			// ลบของเก่า
			courseTeacherRepository.deleteCourseTeachers(id);
			if (courseNew.getTeacherList() != null) {
				for (CourseScheduleDto date : courseNew.getDateList()) {
					courseScheduleService.createCourseSchedule(date);
				}
			}

//			entity.setCourseTeacher(courseTeacherRepository.findAllByCourseId(id));
//			entity.setCourseSchdule(courseScheduleRepository.findAllByCourseId(id));

			return mapEntityEditToDto(entity);

		} catch (RuntimeException e) {
			log.error("updateCourse >>>> " + e.getMessage());
			return null;
		} catch (Exception e) {
			log.error("updateCourse >>>> " + e.getMessage());
			return null;
		}
	}

	private List<CourseScheduleEntity> mapCourseScheduleListToEntities(Long id, List<CourseScheduleDto> dateList) {
		List<CourseScheduleEntity> entities = new ArrayList<CourseScheduleEntity>();
		CourseScheduleEntity entity = new CourseScheduleEntity();
		if (dateList != null) {
			for (CourseScheduleDto date : dateList) {
				entity.setCourseId(id);
				entity.setCourseScheduleDate(date.getCourseScheduleDate());
				entities.add(entity);
			}
		}
		return entities;
	}

	private List<CourseTeacherEntity> mapMemberListToTeacherEntity(Long courseId, List<MemberDto> list) {
		List<CourseTeacherEntity> entities = new ArrayList<CourseTeacherEntity>();
		CourseTeacherEntity entity = new CourseTeacherEntity();
		if (list != null) {
			for (MemberDto teacher : list) {
				entity.setCourseId(courseId);
				entity.setMemberId(teacher.getId());
//				 entity.setMember(memberRepository.findById(teacher.getId()).get());
				entities.add(entity);
			}
		}

		return entities;
	}

	private CourseEntity convDtoToEntity(CourseDto course) {
		CourseEntity entity = new CourseEntity();
		System.out.println(course.toString());
		entity.setCourseId(course.getId());
		entity.setCourseNo(course.getNo());
		entity.setCourseName(course.getName());
		entity.setCourseDetail(course.getDetail());
		entity.setCourseStatus(course.getStatus());
		entity.setCourseStDate(course.getStDate());
		entity.setCourseEndDate(course.getEndDate());
		entity.setCourseConditionMin(course.getConditionMin());
		entity.setCourseCreateDate(course.getCreateDate());
		entity.setCourseLastUpdate(course.getLastUpdate());
		entity.setCourseLocationId(course.getLocationId());
		entity.setCourseCreateBy(course.getMemberId());
		return entity;
	}

	private MembersHasCourseEntity mapDtoToEntity(MembersHasCourseDto body) {
		MembersHasCourseEntity entity = new MembersHasCourseEntity();
		TransportationEntity tran = new TransportationEntity();
		if (body != null) {
			entity.setMembersHasCourseId(body.getMembersHasCourseId());
			entity.setCourseId(body.getCourseId());
			entity.setMemberId(body.getMemberId());
			entity.setRegisterDate(body.getRegisterDate());
			entity.setSenseId(body.getSenseId());
			entity.setMhcStatus(body.getStatus());
			tran.setTransportationId(body.getTransportationId());
			entity.setTranId(tran);
		}
		return entity;
	}

	private MembersHasCourseDto mapEntityToDto(MembersHasCourseEntity body) {
		MembersHasCourseDto dto = new MembersHasCourseDto();
		if (body != null) {
			dto.setMembersHasCourseId(body.getMembersHasCourseId());
			dto.setCourseId(body.getCourseId());
			dto.setMemberId(body.getMemberId());
			dto.setRegisterDate(body.getRegisterDate());
			dto.setStatus(body.getMhcStatus());
			dto.setSenseId(body.getSenseId());
		}
		return dto;
	}

	private CourseScheduleEntity mapDtoToEntity(CourseScheduleDto body) {
		CourseScheduleEntity entity = new CourseScheduleEntity();
		if (body != null) {
			entity.setCourseId(body.getCourseId());
			entity.setCourseScheduleDate(body.getCourseScheduleDate());
		}
		return entity;
	}

	private CourseScheduleDto mapEntityToDto(CourseScheduleEntity body) {
		CourseScheduleDto dto = new CourseScheduleDto();
		if (body != null) {
			dto.setCourseId(body.getCourseId());
			dto.setCourseScheduleDate(body.getCourseScheduleDate());

		}
		return dto;
	}

	private CourseDto mapEntityEditToDto(CourseEntity entity) {
		List<MemberDto> teacherList = new ArrayList<>();
		CourseDto dto = new CourseDto();
		TransportationDto transportationDto = new TransportationDto();
		try {
			if (entity != null) {
				dto.setId(entity.getCourseId());
				dto.setNo(entity.getCourseNo());
				dto.setName(entity.getCourseName());
				dto.setDetail(entity.getCourseDetail());
				dto.setConditionMin(entity.getCourseConditionMin());
				dto.setCreateDate(entity.getCourseCreateDate());
				dto.setLastUpdate(entity.getCourseLastUpdate());
				dto.setLocationId(entity.getLocationId().getLocationId());
				dto.setLocationName(entity.getLocationId().getLocationName());

				if (entity.getTransportationId() != null) {
					transportationDto.setId(entity.getTransportationId());
					dto.setTransportation(transportationDto);
				}

				dto.setMemberId(entity.getCreateBy().getMemberId());
				dto.setMemberFname(entity.getCreateBy().getMemberFname());
				dto.setMemberLname(entity.getCreateBy().getMemberLname());
				dto.setStDate(entity.getCourseStDate());
				dto.setEndDate(entity.getCourseEndDate());

				for (CourseTeacherEntity courseTeacher : entity.getCourseTeacher()) {
					teacherList.add(memberService.getMember(courseTeacher.getMemberId()));
				}
				dto.setTeacherList(teacherList);
				dto.setDateList(courseScheduleService.getCourseScheduleList(entity.getCourseId()));

				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("mapEntityEditToDto >>>> " + e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

	private List<CourseScheduleDto> mapDateEntityListToDto(List<CourseScheduleEntity> courseSchduleList) {
		List<CourseScheduleDto> dtoList = new ArrayList<>();
		for (CourseScheduleEntity courseSchdule : courseSchduleList) {
			dtoList.add(mapDateEntityToDto(courseSchdule));
		}
		return dtoList;
	}

	private CourseScheduleDto mapDateEntityToDto(CourseScheduleEntity courseSchdule) {
		CourseScheduleDto dto = new CourseScheduleDto();
		if (courseSchdule != null) {
			dto.setCourseId(courseSchdule.getCourseId());
			dto.setCourseScheduleDate(courseSchdule.getCourseScheduleDate());
		}
		return dto;
	}

}
