package com.gkoo.open.configuration;

/**
 * @author sanghuncho
 *
 */
public class ServicePath {
    public static final String HOST_ADDRESS_DEV = "http://localhost:3000";
    public static final String HOST_ADDRESS_PROD = "https://gkoo.co.kr";
    public static final String HOST_ADDRESS_WORLD_PROD = "https://www.gkoo.co.kr";
    
    public static final String NOTICE_BOARD_WORLD_PROD = HOST_ADDRESS_WORLD_PROD + "/noticeBoard";
    public static final String FAST_ESTIMATION_WORLD_PROD = HOST_ADDRESS_WORLD_PROD + "/buyingService";
    
    public static final String NOTICE_BOARD_DEV = HOST_ADDRESS_DEV;
    public static final String NOTICE_BOARD_PROD = HOST_ADDRESS_PROD + "/noticeBoard";
    
    public static final String FAST_ESTIMATION_DEV = HOST_ADDRESS_DEV;
    public static final String FAST_ESTIMATION_PROD = HOST_ADDRESS_PROD + "/buyingService";
}
