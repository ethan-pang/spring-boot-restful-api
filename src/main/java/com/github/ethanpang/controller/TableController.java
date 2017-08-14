package com.github.ethanpang.controller;

import com.github.ethanpang.config.datasource.DataSourceType;
import com.github.ethanpang.core.Result;
import com.github.ethanpang.core.ResultGenerator;
import com.github.ethanpang.model.DailyReport;
import com.github.ethanpang.model.User;
import com.github.ethanpang.service.DailyReportService;
import com.github.ethanpang.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ethan on 2017/8/7.
 */
@RestController
@RequestMapping("/table")
public class TableController {
    @Resource
    private DailyReportService dailyReportService;
    @Resource
    private UserService userService;

    @GetMapping("/daily_report")
    @DataSourceType("readonly")
    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    public Result dailyReport(@RequestHeader("Authorization") String token, @RequestParam("page")Integer page, @RequestParam("limit")Integer limit){
        User user= userService.getUserByToken(token);
        Condition condition=new Condition(DailyReport.class);
        Condition.Criteria criteria=condition.createCriteria();
        criteria.andEqualTo("channel",user.getChannel());
        PageHelper.startPage(page, limit);
        List<DailyReport> list = dailyReportService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
