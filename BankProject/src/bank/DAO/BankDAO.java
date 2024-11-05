package bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.DTO.Bank;
import bank.Factory.Connector;

public class BankDAO {
	public static boolean createAccount(Bank b) {
		Connection con = null;
		PreparedStatement ps = null;
		String create = "INSERT INTO USERS VALUES(0,?,?,?,?,?)";
		int res = 0;
		try {
			con = Connector.connect();
			ps = con.prepareStatement(create);
			ps.setString(1, b.getName());
			ps.setString(2, b.getPhone());
			ps.setString(3, b.getEmail());
			ps.setInt(4, b.getBalance());
			ps.setString(5, b.getPin());
			res = ps.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res > 0) {
			return true;
		} else {
			System.out.println("Registration failed..");
			return false;
		}
	}

	public static Bank getDetails(Bank b) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String data = "SELECT * FROM USERS WHERE ACCNO = ? AND PIN = ?";
		try {
			con = Connector.connect();
			ps = con.prepareStatement(data);
			ps.setInt(1, b.getAccno());
			ps.setString(2, b.getPin());
			rs = ps.executeQuery();
			while (rs.next()) {
				b.setAccno(rs.getInt(1));
				b.setName(rs.getString(2));
				b.setPhone(rs.getString(3));
				b.setEmail(rs.getString(4));
				b.setBalance(rs.getInt(5));
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public static int updatePin(int acc, String pass) {
		Connection con = null;
		PreparedStatement ps = null;
		String updatepin = "UPDATE USERS SET PIN = ? WHERE ACCNO = ?";
		int res = 0;
		try {
			con = Connector.connect();
			ps = con.prepareStatement(updatepin);
			ps.setString(1, pass);
			ps.setInt(2, acc);
			res = ps.executeUpdate();
			// System.out.println(res);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}

	public static int getBal(int acno) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int balance = 0;
		String data = "SELECT * FROM USERS WHERE ACCNO = ?";
		try {
			con = Connector.connect();
			ps = con.prepareStatement(data);
			ps.setInt(1, acno);

			rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getInt(5);

			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}

	public static Bank getBeneficiaryAccount(int acno) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String data = "SELECT * FROM USERS WHERE ACCNO = ?";
		Bank b = null;
		try {
			con = Connector.connect();
			ps = con.prepareStatement(data);
			ps.setInt(1, acno);

			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString(2);
				int amt = rs.getInt(5);
				b = new Bank();
				b.setBalance(amt);
				b.setName(name);
			}
			con.commit();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}

	public static int updateBal(int acc, int bal) {
		Connection con = null;
		PreparedStatement ps = null;
		String updateBal = "UPDATE USERS SET BALANCE = ? WHERE ACCNO = ?";
		int res = 0;
		try {
			con = Connector.connect();
			ps = con.prepareStatement(updateBal);
			ps.setInt(1, bal);
			ps.setInt(2, acc);
			res = ps.executeUpdate();
// System.out.println("Current balance : "+getBal(acc)); 
// System.out.println(res); 
		} catch (ClassNotFoundException | SQLException e) {
// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		return res;
	}
}
