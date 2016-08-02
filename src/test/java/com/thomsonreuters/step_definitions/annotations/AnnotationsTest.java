package com.thomsonreuters.step_definitions.annotations;

import com.thomsonreuters.driver.exception.PageOperationException;
import com.thomsonreuters.pageobjects.pages.annotations.FormatType;
import com.thomsonreuters.pageobjects.pages.annotations.SharedAnnotationsPage;
import com.thomsonreuters.pageobjects.utils.User;
import com.thomsonreuters.pageobjects.utils.screen_shot_hook.BaseStepDef;

import org.springframework.util.StringUtils;

/**
 * Created by uc186961 on 01/03/2016.
 */
public class AnnotationsTest extends BaseStepDef {


    private SharedAnnotationsPage sharedAnnotationsPage = new SharedAnnotationsPage();

    protected String getUserFullName(String contact) {
        User user = annotationUsers.get(contact);
        if (StringUtils.isEmpty(user)) {
            throw new PageOperationException("Usernames are not matching between usernameAndPassword properties and plPlusUser username value.");
        }
        return user.getFullName();
    }

    protected String getUserNameStartswithLastName(String contact) {
        User user = annotationUsers.get(contact);
        if (StringUtils.isEmpty(user)) {
            throw new PageOperationException("Usernames are not matching between usernameAndPassword properties and plPlusUser username value.");
        }
        return user.getLastName() + ", " + user.getFirstName();
    }



    protected FormatType getFormatType(String style) {
        return FormatType.valueOf(style.toUpperCase().trim());
    }

}
