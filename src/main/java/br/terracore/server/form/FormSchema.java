package br.terracore.server.form;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "\"formSchema\"")
@XmlRootElement
public class FormSchema implements Serializable {
        
        @Id
        @GeneratedValue
        private Long id;
        
        private String  name;
        
        private String  description;
        
        private String  type;
        
        /**
         * Um base64 de um ícone ?
         * */
        private String  icon;
        
        private String  schema;
        
        public FormSchema() {}
        
        public FormSchema(
                          Long id,
                          String name,
                          String description,
                          String type,
                          String icon,
                          String schema) {
                super();
                this.id = id;
                this.name = name;
                this.description = description;
                this.type = type;
                this.icon = icon;
                this.schema = schema;
        }
        
        public Long getId() {
                return id;
        }
        
        public void setId(Long id) {
                this.id = id;
        }
        
        public String getName() {
                return name;
        }
        
        public void setName(String name) {
                this.name = name;
        }
        
        public String getDescription() {
                return description;
        }
        
        public void setDescription(String description) {
                this.description = description;
        }
        
        public String getType() {
                return type;
        }
        
        public void setType(String type) {
                this.type = type;
        }
        
        public String getIcon() {
                return icon;
        }
        
        public void setIcon(String icon) {
                this.icon = icon;
        }
        
        public String getSchema() {
                return schema;
        }
        
        public void setSchema(String schema) {
                this.schema = schema;
        }
        
        /*
        @Override
        public String toString() {
                JSONObject data = new JSONObject();
                
                try {
                        data.put("id", id);
                        data.put("name", name);
                        data.put("description", description);
                        data.put("type", type);
                        data.put("icon", icon);
                        data.put("schema", schema);
                }
                catch (JSONException e) {
                        e.printStackTrace();
                }
                
                return data.toString();
        }*/
}
