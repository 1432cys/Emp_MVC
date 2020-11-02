package Model;

public class EmpDto {
	private String id;
	private String pwd;
	
	private String name;
	private String gender;
	private int age;
	private String email;
	
	public EmpDto() {
		
	}
	
	public EmpDto(String id, String pwd, String name, int age, String gender, String email) {
		super();
		System.out.println("Emp constructor");
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Dto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", Gender=" + gender + ", age="
				+ age + "]";
	}
	
	
	
	
	
}
