package bank.App;

import java.util.Scanner;

import bank.DAO.BankDAO;
import bank.DTO.Bank;

public class BankApp {
	public static void main(String[] args) {
		boolean cond = true;
		while (cond) {
			System.out.println("Enter 1 for Account creation");
			System.out.println("Enter 2 for Details ");
			System.out.println("Enter 3 for check balance");
			System.out.println("Enter 4 for deposit");
			System.out.println("Enter 5 for withdraw");
			System.out.println("Enter 6 for pin change ");
			System.out.println("Enter 7 to transfer the amount  ");
			System.out.println("Enter 8 for exit");
			Scanner sc = new Scanner(System.in);
			int option = sc.nextInt();
			Bank b = new Bank();
			switch (option) {
			case 1:
				sc.nextLine();
				System.out.println("Enter the name : ");
				String name = sc.nextLine();
				System.out.println("Enter the Phone number : ");
				String phone = sc.next();
				System.out.println("Enter the email : ");
				String mail = sc.next();
				System.out.println("Enter the Initial Deposit : ");
				int bal = sc.nextInt();

				System.out.println("Enter the PIN : ");
				String pin = sc.next();
				System.out.println("Confirm your PIN : ");
				String c = sc.next();
				b.setName(name);
				b.setBalance(bal);
				b.setEmail(mail);
				b.setPhone(phone);
				if (pin.equals(c)) {
					b.setPin(pin);
				}
				boolean res = BankDAO.createAccount(b);
				if (res) {
					System.out.println("Happy to have " + name + " here with Accno : " + b.getAccno());
					System.out.println("Thank you for creating the account ...");
				} else {
					System.out.println("Account  creation failed...");
				}
				break;
			case 2:
				System.out.println("Enter the acccount number : ");
				int acno = sc.nextInt();
				System.out.println("Enter the password : ");
				pin = sc.next();
				b.setAccno(acno);
				b.setPin(pin);
				Bank user = BankDAO.getDetails(b);
				System.out.println("Name : " + user.getName());
				System.out.println("Email : " + user.getEmail());
				System.out.println("Phone : " + user.getPhone());
				System.out.println("Balance : " + user.getBalance());
				break;
			case 3:
				System.out.println("Enter the acccount number : ");
				acno = sc.nextInt();
				System.out.println("Enter the password : ");
				pin = sc.next();
				b.setAccno(acno);
				b.setPin(pin);
				user = BankDAO.getDetails(b);
				System.out.print("Balance =  ");
				System.out.println(user.getBalance());
				break;
			case 4:
				System.out.println("Enter the acccount number : ");
				acno = sc.nextInt();
				System.out.println("Enter the amount to deposit : ");
				bal = sc.nextInt();
				if (bal > 0) {
					int oldBalance = BankDAO.getBal(acno);
					int newBalance = oldBalance + bal;
					int upbal = BankDAO.updateBal(acno, newBalance);
					if (upbal > 0) {
						System.out.println("Desposit succesfull..");
					} else {
						System.out.println("Deposit failed..");
					}
				} else {
					System.out.println("Deposit amount should be greater than 0");
				}
				break;
			case 5:
				System.out.println("Enter the acccount number : ");
				acno = sc.nextInt();
				System.out.println("Enter the password : ");
				pin = sc.next();
				b.setAccno(acno);
				b.setPin(pin);
				user = BankDAO.getDetails(b);
				if (user != null) {
					System.out.println("Enter the withdrawl amount : ");
					int withamt = sc.nextInt();
					if (withamt <= user.getBalance()) {
						int upbal = user.getBalance() - withamt;
						int a = BankDAO.updateBal(acno, upbal);
						if (a > 0) {
							System.out.println("Withdraw success of amount " + withamt);
						}
					} else {
						System.out.println("Insufficient funds...");
					}
				} else {
					System.out.println("No such account found..");
				}
				break;
			case 6:
				System.out.println("Enter the account number : ");
				acno = sc.nextInt();
				System.out.println("Enter the Current pin ");
				pin = sc.next();
				b.setAccno(acno);
				b.setPin(pin);
				user = BankDAO.getDetails(b);
				if (user != null) {
					System.out.println("Enter the new pin : ");
					String npin = sc.next();
					int up = BankDAO.updatePin(b.getAccno(), npin);
					if (up > 0) {

						System.out.println("Updation success...");
					} else {
						System.out.println("Updation failed..");
					}
				} else {
					System.out.println("No such account found...");
				}
				break;
			case 7:
				System.out.println("Enter the beneficiary account number : ");
				int bAcno = sc.nextInt();
				System.out.println("Enter the amount tp Transfer : ");
				int tAmt = sc.nextInt();
				System.out.println("Enter the your account number : ");
				acno = sc.nextInt();
				System.out.println("Enter the pin ");
				pin = sc.next();
				b.setAccno(acno);
				b.setPin(pin);
				Bank beUser = BankDAO.getBeneficiaryAccount(bAcno);
				user = BankDAO.getDetails(b);
				int tran = 0;
				if (user != null) {
					if (beUser != null) {
						if (user.getBalance() >= tAmt) {
							int bbal = beUser.getBalance() + tAmt;
							int ubal = user.getBalance() - tAmt;

							tran = BankDAO.updateBal(bAcno, bbal);
							BankDAO.updateBal(acno, ubal);
							if (tran > 0) {
								System.out.println("Transaction success... for : " + beUser.getName());
							} else {
								System.out.println("Transaction failed..");
							}
						} else {
							System.out.println("Insufficient balancee ..");
						}
					} else {
						System.out.println("No such Beneficiary account found..");
					}
				} else {
					System.out.println("No such Account found ");
				}
				break;

			case 8:
				cond = false;
				System.out.println("Thank your for using visit again :) ");
				break;
			default:
				System.out.println("No such option found...");
			}
		}
	}
}