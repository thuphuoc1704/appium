package com.vne.interfaces;

public class BasePageUI {
	public static final String LIST_MENU="xpath=(//android.widget.TextView[@resource-id='fr.playsoft.vnexpress:id/folder'])[%s]";
	public static final String ICON_CIRCLE_MODE_READ="xpath=//android.widget.SeekBar/android.widget.SeekBar";
	public static final String TITLE_PODCAST="id=fr.playsoft.vnexpress:id/title";
	public static final String ICON_AA="id=fr.playsoft.vnexpress:id/img_font_large";
	public static final String ICON_MENU="id=fr.playsoft.vnexpress:id/icon_tab_notification";
	public static final String ICON_MODE_READ="id=fr.playsoft.vnexpress:id/tab_read_mode";
	public static final String ICON_CLOSE_MODE_READ="id=fr.playsoft.vnexpress:id/close_bottom_sheet";
	public static final String TEXTBOX_DYNAMIC="id=%s";
	public static final String MENU_IN_ICON_MENU_DYNAMIC="xpath=//android.widget.TextView[@resource-id='fr.playsoft.vnexpress:id/parent' and @text='%s']";
	public static final String MENU_IN_FOLDER_DYMAMIC="xpath=//android.widget.TextView[@resource-id='fr.playsoft.vnexpress:id/folder' and @text='%s']";
	public static final String PODCAST_PROGRAM="xpath=//android.widget.TextView[@resource-id='fr.playsoft.vnexpress:id/text_show' and @text='%s']";
	public static final String ICON_LIVE="id=fr.playsoft.vnexpress:id/ripple_live";
	
	//web
	public static final String WEB_MENU_IN_FOLDER="xpath=//ul[@class='menu_bread']//a[@title='%s']";
	public static final String WEB_SUB_MENU_IN_FOLDER_LV1="xpath=//section[contains(@class,'submenu')]//a[@title='%s']";
	public static final String WEB_SUB_MENU_IN_FOLDER_LV2="xpath=//a[@title='%s']/following-sibling::select[@id='media_type']";
}
