package com.gkoo.open.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.gkoo.open.data.NoticeData;
import com.gkoo.open.db.ConnectionDB;
import com.gkoo.open.service.NoticeBoardService;

/**
 *
 * @author sanghuncho
 *
 * @since  09.01.2020
 *
 */
@Service
public class NoticeBoardServiceImpl implements NoticeBoardService  {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String FETCH_NOTICES = "select * from notices";
    
    @Override
    public List<NoticeData> getNotices() {
        ConnectionDB.connectSQL();
        List<NoticeData> notices = new ArrayList<>();
        ResultSet resultSet = null;
        
        try (Connection conn = ConnectionDB.getConnectInstance();
                PreparedStatement psmt = conn.prepareStatement(FETCH_NOTICES);){
            resultSet = psmt.executeQuery();
            notices = writeNotices(resultSet);
        } catch (SQLException e) {
            String error = "Error fetching notices";
            LOGGER.error(error, e);
        }
        return notices;
    }
    
    private static List<NoticeData> writeNotices(ResultSet rs) throws SQLException {
        List<NoticeData> notices = new ArrayList<>();
        while (rs.next()) {
            NoticeData notice = new NoticeData();
            notice.setNoticeid(rs.getInt("noticeid"));
            notice.setNoticeTitle(rs.getString("notice_title"));
            notice.setNoticeContent(rs.getString("notice_content"));
            notice.setNoticeDate(rs.getDate("notice_date"));
            notices.add(notice);
        }
        return notices;
    }
}
