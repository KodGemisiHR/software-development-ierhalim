/*
 * Copyright (C) 10, 2015 Kod Gemisi Ltd. <foss@kodgemisi.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kodgemisi.web.sample.config.listener;

import com.kodgemisi.web.sample.model.Item;
import com.kodgemisi.web.sample.model.User;
import com.kodgemisi.web.sample.service.ItemService;
import com.kodgemisi.web.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by destan on 19.10.2015.
 */
@Component
public class DefaultUserCreationListener implements ApplicationListener {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {

            // FIXME check by username/email
            if (userService.getById(4L) != null) {
                // default user is already created
                return;
            }

            Item item1 = new Item("Test 1", "lorem ipsum","0001");
            Item item2 = new Item("Test 2", "lorem ipsum","0002");
            Item item3 = new Item("Test 3", "lorem ipsum","0003");


            User defaultUser = new User();
            defaultUser.setName("Default");
            defaultUser.setSurname("User");

//            defaultUser.getItems().add(item1)..;
//            defaultUser.getItems().add(item2);
//            defaultUser.getItems().add(item3);

            item1.setOwner(defaultUser);
            item2.setOwner(defaultUser);
            item3.setOwner(defaultUser);

            defaultUser.setEmail("user@kodgemisi.com");
            defaultUser.setPassword("123qwe123");
            userService.create(defaultUser);

            itemService.create(item1);
            itemService.create(item2);
            itemService.create(item3);

        }
    }
}
