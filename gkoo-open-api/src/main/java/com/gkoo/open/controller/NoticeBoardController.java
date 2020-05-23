package com.gkoo.open.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gkoo.open.configuration.ServicePath;
import com.gkoo.open.data.NoticeData;
import com.gkoo.open.service.NoticeBoardService;
/**
 *
 * @author sanghuncho
 *
 * @since  09.01.2020
 *
 */
@RestController
public class NoticeBoardController {
    private static final Logger LOGGER = LogManager.getLogger();
    private final NoticeBoardService noticeBoardService;
    
    @Autowired
    public NoticeBoardController(NoticeBoardService noticeBoardService){
        this.noticeBoardService = noticeBoardService;
    }
    
    @CrossOrigin(origins = {ServicePath.NOTICE_BOARD_DEV, ServicePath.NOTICE_BOARD_PROD})
    @RequestMapping("/getNoticeList")
    public List<NoticeData> requestNotices(HttpServletRequest request) throws SQLException {
        return noticeBoardService.getNotices();
    }
}
