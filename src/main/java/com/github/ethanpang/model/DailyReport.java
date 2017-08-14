package com.github.ethanpang.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Table(name = "daily_report")
public class DailyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSONField(serialize = false)
    private Integer id;

    @Column(name = "stats_date")
    private Date statsDate;

    private Long channel;

    @Column(name = "stats_reg")
    private Long statsReg;

    @Column(name = "stats_dau")
    private Long statsDau;

    @Column(name = "stats_pay")
    private Long statsPay;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return stat_date
     */
    public Date getStatsDate() {
        return statsDate;
    }

    public void setStatsDate(Date statsDate) {
        this.statsDate = statsDate;
    }

    /**
     * @return channel
     */
    public Long getChannel() {
        return channel;
    }

    /**
     * @param channel
     */
    public void setChannel(Long channel) {
        this.channel = channel;
    }

    /**
     * @return stats_reg
     */
    public Long getStatsReg() {
        return statsReg;
    }

    /**
     * @param statsReg
     */
    public void setStatsReg(Long statsReg) {
        this.statsReg = statsReg;
    }

    /**
     * @return stats_dau
     */
    public Long getStatsDau() {
        return statsDau;
    }

    /**
     * @param statsDau
     */
    public void setStatsDau(Long statsDau) {
        this.statsDau = statsDau;
    }

    /**
     * @return stats_pay
     */
    public Long getStatsPay() {
        return statsPay;
    }

    /**
     * @param statsPay
     */
    public void setStatsPay(Long statsPay) {
        this.statsPay = statsPay;
    }
}