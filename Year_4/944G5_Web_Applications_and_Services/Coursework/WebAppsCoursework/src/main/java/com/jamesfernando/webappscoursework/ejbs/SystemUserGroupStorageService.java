/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamesfernando.webappscoursework.ejbs;

import com.jamesfernando.webappscoursework.entitys.SystemUserGroup;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author James
 */
@Local
public interface SystemUserGroupStorageService {
        public List<SystemUserGroup> getSystemUserGroupList();

    public void insertSystemUserGroup(String sussexId, String groupName);
}
