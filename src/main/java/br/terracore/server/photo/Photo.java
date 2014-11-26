package br.terracore.server.photo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.terracore.server.form.Form;

@Entity
@Table(name = "\"photo\"")
@XmlRootElement
public class Photo implements Serializable {
        @Id
        @GeneratedValue
        private Long   id;
        @Lob
        private byte[] blob;
        
        private String path;
        
        @ManyToOne(cascade = CascadeType.ALL, targetEntity = Form.class)
        private Form   form;
        
        public Photo() {
                // TODO Auto-generated constructor stub
        }
        
        public Photo(Long id, byte[] blob, String path, Form form) {
                super();
                this.id = id;
                this.path = path;
                this.form = form;
                this.blob = blob;
        }
        
        public Long getId() {
                return id;
        }
        
        public void setId(Long id) {
                this.id = id;
        }
        
        public String getPath() {
                return path;
        }
        
        public void setPath(String path) {
                this.path = path;
        }
        
        public Form getForm() {
                return form;
        }
        
        public void setForm(Form form) {
                this.form = form;
        }
        
        public byte[] getBlob() {
                return blob;
        }
        
        public void setBlob(byte[] blob) {
                this.blob = blob;
        }
        
}
