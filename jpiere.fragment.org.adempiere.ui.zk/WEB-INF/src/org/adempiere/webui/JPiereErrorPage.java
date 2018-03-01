/******************************************************************************
 * Product: JPiere                                                            *
 * Copyright (C) Hideaki Hagiwara (h.hagiwara@oss-erp.co.jp)                  *
 *                                                                            *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY.                          *
 * See the GNU General Public License for more details.                       *
 *                                                                            *
 * JPiere is maintained by OSS ERP Solutions Co., Ltd.                        *
 * (http://www.oss-erp.co.jp)                                                 *
 *****************************************************************************/

package org.adempiere.webui;

import javax.servlet.ServletRequest;

import org.adempiere.webui.part.AbstractUIPart;
import org.adempiere.webui.theme.ThemeManager;
import org.zkoss.web.servlet.Servlets;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.East;
import org.zkoss.zul.North;
import org.zkoss.zul.South;
import org.zkoss.zul.West;

/**
 *
 * @author  <a href="mailto:agramdass@gmail.com">Ashley G Ramdass</a>
 * @author  Low Heng Sin
 * @date    Mar 3, 2007
 * @version $Revision: 0.10 $
 * @author Hideaki Hagiwara(h.hagiwara@oss-erp.co.jp)
 */
public class JPiereErrorPage extends AbstractUIPart
{
	private Borderlayout layout;

    public JPiereErrorPage()
    {
    	;
    }

    protected Component doCreatePart(Component parent)
    {
    	PageDefinition pageDefintion = Executions.getCurrent().getPageDefinition(ThemeManager.getThemeResource("zul/error/error.zul"));
    	Component loginPage = Executions.createComponents(pageDefintion, parent, null);

        layout = (Borderlayout) loginPage.getFellow("layout");

        JPiereErrorPageWindow loginWindow = (JPiereErrorPageWindow) loginPage.getFellow("loginWindow");
        loginWindow.init();

        boolean mobile = false;
		if (Executions.getCurrent().getBrowser("mobile") !=null) {
			mobile = true;
		} else {
			String ua = Servlets.getUserAgent((ServletRequest) Executions.getCurrent().getNativeRequest());
			ua = ua.toLowerCase();
			if (ua.contains("ipad") || ua.contains("iphone") || ua.contains("android"))
				mobile = true;
		}

        West west = layout.getWest();
        if (west.getFirstChild() != null && west.getFirstChild().getFirstChild() != null) {
    		west.setCollapsible(true);
    		west.setSplittable(true);
        	if (mobile) {
        		west.setOpen(false);
        	}
        } else {
        	west.setVisible(false);
        }

        East east = layout.getEast();
        if (east.getFirstChild() != null && east.getFirstChild().getFirstChild() != null) {
        	if (mobile) {
        		east.setCollapsible(true);
        		east.setOpen(false);
        	}
        } else {
        	east.setVisible(false);
        }

        North north = layout.getNorth();
        if (north.getFirstChild() == null || north.getFirstChild().getFirstChild() == null) {
        	north.setVisible(false);
        }

        South south = layout.getSouth();
        if (south.getFirstChild() == null || south.getFirstChild().getFirstChild() == null) {
        	south.setVisible(false);
        }

        return layout;
    }


	public Component getComponent() {
		return layout;
	}

}
