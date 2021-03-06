package br.terracore.server.task;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.terracore.server.address.Address;
import br.terracore.server.form.Form;
import br.terracore.server.user.User;

@Entity
@Table(name = "task")
@XmlRootElement
public class Task implements Serializable {
        
        private static final long serialVersionUID = 1L;
        
        @Id
        @GeneratedValue
        private Long              id;
        
        @OneToOne(
                  cascade = { CascadeType.PERSIST, CascadeType.MERGE },
                  fetch = FetchType.EAGER)
        private Address           address;
        
        @ManyToOne(
                   cascade = { CascadeType.PERSIST, CascadeType.MERGE },
                   fetch = FetchType.EAGER)
        private User              user;
        
        @OneToOne(
                  cascade = { CascadeType.PERSIST, CascadeType.MERGE },
                  fetch = FetchType.EAGER)
        private Form              form;
        
        @Basic
        @Column(name = "done", columnDefinition = "BIT", length = 1)
        private boolean           done;                 // sincronizado com o servidor?
                                                         
        public Task() {
                // TODO Auto-generated constructor stub
        }
        
        public Task(Long id, Address address, User user, Form form, boolean done) {
                super();
                this.id = id;
                this.address = address;
                this.user = user;
                this.form = form;
                this.done = done;
        }
        
        public Long getId() {
                return id;
        }
        
        public void setId(Long id) {
                this.id = id;
        }
        
        public Address getAddress() {
                return address;
        }
        
        public void setAddress(Address address) {
                this.address = address;
        }
        
        public User getUser() {
                return user;
        }
        
        public void setUser(User user) {
                this.user = user;
        }
        
        public Form getForm() {
                return form;
        }
        
        public void setForm(Form form) {
                this.form = form;
        }
        
        public boolean isDone() {
                return done;
        }
        
        public void setDone(boolean done) {
                this.done = done;
        }
        
        public static long getSerialversionuid() {
                return serialVersionUID;
        }
        
        @Override
        public String toString() {
                return "Task [id=" + id + ", address=" + address + ", user=" + user + ", form=" + form + ", done=" + done + "]";
        }
        
}