package com.app;

import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Demo

{
	BufferedReader br = null;
	PreparedStatement pstmt = null;
	Connection con = null;
	ResultSet rs = null;

	public void login() throws IOException, SQLException,
			ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz",
				"root", "");

		br = new BufferedReader(new InputStreamReader(System.in));
		boolean b = true;
		while (b) {
			System.out.println("***enter admin name***");
			String username1 = br.readLine();
			System.out.println("***enter password***");
			String password1 = br.readLine();
			pstmt = con
					.prepareStatement("select *from admin where username=? and password=?");
			pstmt.setString(1, username1);
			pstmt.setString(2, password1);

			rs = pstmt.executeQuery();

			boolean fnd = rs.next();
			if (fnd) {
				System.out.println("*****login successful*****");
				for (int i = 0; i < 8; i++) {
					pstmt = con
							.prepareStatement("insert into quiz values(?,?,?,?,?,?,?)");

					System.out.println("enter the Qid");
					int Qid = Integer.parseInt(br.readLine());
					pstmt.setInt(1, Qid);
					System.out.println("enter the question");
					String question = br.readLine();
					pstmt.setString(2, question);
					System.out.println("(a)");
					String a = br.readLine();
					pstmt.setString(3, a);
					System.out.println("(b)");
					String b1 = br.readLine();
					pstmt.setString(4, b1);
					System.out.println("(c)");
					String c = br.readLine();
					pstmt.setString(5, c);
					System.out.println("(d)");
					String d = br.readLine();
					pstmt.setString(6, d);
					System.out.println(" correct answer");
					String correctanswer = br.readLine();
					pstmt.setString(7, correctanswer);
					pstmt.executeUpdate();

				}
				System.out.println("Record Inserted Successfully");
				break;
			} else {
				System.out.println("*****login unsuccessful*****");
				System.out.println("Plzz enter correct Username and Password");

			}
		}
	}

	public void userlogin()

	{
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/quiz", "root", "");

			br = new BufferedReader(new InputStreamReader(System.in));
			boolean b = true;

			while (b) {
				System.out.println("enter user name");
				String username1 = br.readLine();
				System.out.println("enter password");
				String password1 = br.readLine();
				pstmt = con

						.prepareStatement("select * from users where username=? and password=?");
				pstmt.setString(1, username1);
				pstmt.setString(2, password1);

				rs = pstmt.executeQuery();

				boolean fnd1 = rs.next();
				if (fnd1) {
					System.out.println("login successful");
					break;
				} else {
					System.out.println("login unsuccessful");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			// BufferedReader br = null;
			ResultSet rs = null;
			int total = 0;
			int correct = 0;
			int incorrect = 0;
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/quiz", "root", "");

			System.out.println("***Start The Quiz***");
			System.out.println();
			rs = pstmt.executeQuery("select * from quiz");
			while (rs.next()) {

				System.out.print(("Que-") + rs.getInt(1) + "\t");

				System.out.println(rs.getString(2) + "\t");
				System.out.println();
				System.out.println(("(a)") + " " + rs.getString(3) + "\t");
				System.out.println(("(b)") + " " + rs.getString(4) + "\t");
				System.out.println(("(c)") + " " + rs.getString(5) + "\t");
				System.out.println(("(d)") + " " + rs.getString(6) + "\t");
				System.out.println();
				System.out.println("answer");
				/*
				 * pstmt = con .prepareStatement(
				 * "select correctanswer from quiz where question='?' and correctanswer=?"
				 * ); String correctanswer = br.readLine(); pstmt.setString(1,
				 * correctanswer); rs = pstmt.executeQuery();
				 */
				String ch = br.readLine();
				if (ch.equalsIgnoreCase(rs.getString(7))) {
					System.out.println("correct answer you got 1 marks");
					total++;
					correct++;

				} else {
					System.out.println("incorrect answer");
					incorrect++;
				}
				System.out.println();

			}
			System.out.println("Quiz completed");
			System.out.println("Total Marks " + total + "/10");
			System.out.println("Correct Answer " + correct);
			System.out.println("incorrect Answer " + incorrect);
			System.out.println("*** tnxx for playing Quiz***");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

public class Quiz {
	public static void main(String[] args) {
		try {
			Connection con = null;
			PreparedStatement pstmt;
			BufferedReader br = null;
			ResultSet rs = null;
			int count = 0;
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/quiz", "root", "");

			while (true) {
				System.out.println();
				System.out.println("1:For Admin Registration ");
				System.out.println("2:For User Registration");
				System.out.println("3:Log-in Admin");
				System.out.println("4:Log-in User");

				br = new BufferedReader(new InputStreamReader(System.in));
				int choice = Integer.parseInt(br.readLine());

				switch (choice) {
				case 1:
					pstmt = con
							.prepareStatement("insert into admin values(?,?)");

					System.out.println("enter admin username");
					String username = br.readLine();
					System.out.println("enter admin password");
					String password = br.readLine();
					pstmt.setString(1, username);
					pstmt.setString(2, password);
					pstmt.executeUpdate();
					System.out.println("Admin Regestered Successfully");

					break;

				case 2:

					pstmt = con
							.prepareStatement("insert into users values(?,?)");
					System.out.println("enter username");
					String name = br.readLine();
					System.out.println("enter user password");
					String pass = br.readLine();
					pstmt.setString(1, name);
					pstmt.setString(2, pass);
					pstmt.executeUpdate();
					System.out.println("Regestered User successful");

					break;

				case 3:

					System.out.println("For Log In Admin");
					Demo d = new Demo();
					d.login();
					break;
				case 4:
					System.out.println("For Log in Student");
					Demo d1 = new Demo();
					d1.userlogin();
					d1.start();
					break;

				default:
					System.out.println("*****Plzz Enter Between (1-4)*****");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
