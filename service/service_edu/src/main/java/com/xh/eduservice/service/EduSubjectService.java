package com.xh.eduservice.service;

import com.xh.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.eduservice.entity.vo.SubjectNestedVo;
import com.xh.eduservice.entity.vo.SubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author hser
 * @since 2021-03-20
 */
public interface EduSubjectService extends IService<EduSubject> {
    void importSubjectData(MultipartFile file, EduSubjectService subjectService);
    List<SubjectNestedVo> nestedList();

}
