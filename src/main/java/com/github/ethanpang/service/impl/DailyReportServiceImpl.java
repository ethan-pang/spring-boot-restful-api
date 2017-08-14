package com.github.ethanpang.service.impl;

import com.github.ethanpang.dao.DailyReportMapper;
import com.github.ethanpang.model.DailyReport;
import com.github.ethanpang.service.DailyReportService;
import com.github.ethanpang.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/08/07.
 */
@Service
@Transactional
public class DailyReportServiceImpl extends AbstractService<DailyReport> implements DailyReportService {
    @Resource
    private DailyReportMapper dailyReportMapper;

}
