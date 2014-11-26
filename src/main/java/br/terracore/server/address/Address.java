package br.terracore.server.address;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "\"address\"")
@XmlRootElement
public class Address implements Serializable {
        @Id
        @GeneratedValue
        private Long id;
        
        private String  name;
        
        private String  number;
        
        private String  extra;       // complemento
                                      
        private Double  coordx;
        
        private Double  coordy;
        
        private String  postalCode;
        
        private String  city;
        
        private String  state;
        
        private String  featureId;   // O idenfificador da feiÃ§Ã£o (SSQQQNNNN),
                                      
        private String  neighborhood;
        
        public Address() {
                // TODO Auto-generated constructor stub
        }
        
        public Address(
                       Long id,
                       String name,
                       String number,
                       String extra,
                       Double coordx,
                       Double coordy,
                       String postalCode,
                       String city,
                       String state,
                       String featureId) {
                super();
                this.id = id;
                this.name = name;
                this.number = number;
                this.extra = extra;
                this.coordx = coordx;
                this.coordy = coordy;
                this.postalCode = postalCode;
                this.city = city;
                this.state = state;
                this.featureId = featureId;
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
        
        public String getNumber() {
                return number;
        }
        
        public void setNumber(String number) {
                this.number = number;
        }
        
        public String getExtra() {
                return extra;
        }
        
        public void setExtra(String extra) {
                this.extra = extra;
        }
        
        public Double getCoordx() {
                return coordx;
        }
        
        public void setCoordx(Double coordx) {
                this.coordx = coordx;
        }
        
        public Double getCoordy() {
                return coordy;
        }
        
        public void setCoordy(Double coordy) {
                this.coordy = coordy;
        }
        
        public String getPostalCode() {
                return postalCode;
        }
        
        public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
        }
        
        public String getCity() {
                return city;
        }
        
        public void setCity(String city) {
                this.city = city;
        }
        
        public String getState() {
                return state;
        }
        
        public void setState(String state) {
                this.state = state;
        }
        
        public String getFeatureId() {
                return featureId;
        }
        
        public void setFeatureId(String featureId) {
                this.featureId = featureId;
        }
        
        public String getNeighborhood() {
                return neighborhood;
        }
        
        public void setNeighborhood(String neighborhood) {
                this.neighborhood = neighborhood;
        }
        
        @Override
        public String toString() {
                return "Address [id=" + id + ", name=" + name + ", number=" + number + ", extra=" + extra + ", coordx=" + coordx + ", coordy=" + coordy + ", postalCode=" + postalCode + ", city=" + city + ", state=" + state + ", featureId=" + featureId + ", neighborhood=" + neighborhood + "]";
        }
        
}
