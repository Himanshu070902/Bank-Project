package bank.DTO;

public class Bank {
	private String name;
	private int Accno;
	private int Balance;
	private String pin;
	private String email;
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Bank() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccno() {
		return Accno;
	}

	public void setAccno(int accno) {
		Accno = accno;
	}

	public int getBalance() {
		return Balance;
	}

	public void setBalance(int balance) {
		Balance = balance;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

}