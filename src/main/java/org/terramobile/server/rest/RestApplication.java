package org.terramobile.server.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.terracore.server.address.Address;
import br.terracore.server.form.Form;
import br.terracore.server.form.FormSchema;
import br.terracore.server.task.Task;
import br.terracore.server.user.User;

@ApplicationPath("/rest")
public class RestApplication extends Application {
        public RestApplication() {}
        
}