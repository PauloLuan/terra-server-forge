package br.terracore.server.form;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "\"form\"")
@XmlRootElement
public class Form implements Serializable {
        
        @Id
        @GeneratedValue
        private Long       id;
        
        @ManyToOne(cascade = CascadeType.ALL, targetEntity = FormSchema.class)
        private FormSchema schema;
        
        private Date       date;
        
        private String     resultFill;
        
        public Form() {}
        
        public Form(Long id, FormSchema schema, Date date, String resultFill) {
                super();
                this.id = id;
                this.schema = schema;
                this.date = date;
                this.resultFill = resultFill;
        }
        
        public Long getId() {
                return id;
        }
        
        public void setId(Long id) {
                this.id = id;
        }
        
        public FormSchema getSchema() {
                return schema;
        }
        
        public void setSchema(FormSchema schema) {
                this.schema = schema;
        }
        
        public Date getDate() {
                return date;
        }
        
        public void setDate(Date date) {
                this.date = date;
        }
        
        public String getResultFill() {
                return resultFill;
        }
        
        public void setResultFill(String resultFill) {
                this.resultFill = resultFill;
        }
        
        /*
         * @Override public String toString() { JSONObject data = new
         * JSONObject();
         * 
         * try { data.put("id", id); data.put("schema", schema);
         * data.put("date", date); data.put("result", resultFill); } catch
         * (JSONException e) { e.printStackTrace(); }
         * 
         * return data.toString(); }
         */
        
}
