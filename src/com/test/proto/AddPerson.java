package com.test.proto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.test.proto.AddressBookProtos.AddressBook;
import com.test.proto.AddressBookProtos.Person;

class AddPerson {
	/**
	 * 将用户输入的Person message写入输出流中  
	 * @param stdin 输入流
	 * @param stdout 打印输出流
	 * @return Person类
	 * @throws IOException
	 */
	static Person PromptForAddress(BufferedReader stdin,PrintStream stdout)
			throws IOException {
		
		Person.Builder person = Person.newBuilder();
		stdout.print("Enter person ID: ");
		person.setId(Integer.valueOf(stdin.readLine()));

		stdout.print("Enter name: ");
		person.setName(stdin.readLine());

		//空白表示没有
		stdout.print("Enter email address (blank for none): ");
		String email = stdin.readLine();
		if (email.length() > 0){
			person.setEmail(email);
		}
		while (true) {
			//按下Enter键结束输入
			stdout.print("Enter a phone number (or leave blank to finish): ");
			String number = stdin.readLine();
			if (number.length() == 0) {
				break;
			}
			
			Person.PhoneNumber.Builder phoneNumber = Person.PhoneNumber.newBuilder().setNumber(number);
			
			//输入完成之后需要确定你输入的是手机号、家庭电话还是工作电话
			stdout.print("Is this a mobile, home, or work phone? ");
			String type = stdin.readLine();
			if (type.equals("mobile")) {
				phoneNumber.setType(Person.PhoneType.MOBILE);
			} else if(type.equals("home")){
					phoneNumber.setType(Person.PhoneType.HOME);
			} else if (type.equals("work")) {
				phoneNumber.setType(Person.PhoneType.WORK);
			} else {
				stdout.println("Unknown phone type.Using default.");
			}
			person.addPhone(phoneNumber);
			}
			return person.build();
		}
	
	//Main function:  Reads the entire address book from a file,
	//adds one person based on user input, then writes it back out to the same
	//file.  
	public static void main(String[] args)
			throws Exception {
		
//		if (args.length != 1) {
//			System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
//			System.exit(-1);
//			}
		AddressBook.Builder addressBook = AddressBook.newBuilder();
		
		// 检验是否存在这个文件
		try {
			addressBook.mergeFrom(new FileInputStream("src/Book/TestPerson.book"));
			} catch (FileNotFoundException e) {
				System.out.println("src/Book/TestPerson.book" + ": File not found.Creating a new file.");
			}    
		
		//将这条Person message添加到AddressBook中
		addressBook.addPerson(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),System.out));
		
		//将新建的AddressBook写入文件当中
		FileOutputStream output = new FileOutputStream("src/Book/TestPerson.book");
		addressBook.build().writeTo(output);
    	output.close();  
    }
}
