package com.test.proto;

import java.io.FileInputStream;

import com.test.proto.AddressBookProtos.AddressBook;
import com.test.proto.AddressBookProtos.Person;

class ListPeople {
	/**
	 * 迭代遍历并且打印文件中所包含的信息
	 * @param addressBook AddressBook对象
	 */
	static void Print(AddressBook addressBook) {
		for (Person person: addressBook.getPersonList()){
			System.out.println("Person ID: " + person.getId());
			System.out.println("Name: " + person.getName());
			if (person.hasEmail()) {
				System.out.println("E-mail address:"+ person.getEmail());
				}
			for (Person.PhoneNumber phoneNumber : person.getPhoneList()) {
				switch (phoneNumber.getType()) {
				case MOBILE:
					System.out.print("Mobile phone #: ");
					break;    
				case HOME:
					System.out.print("Home phone #: ");
					break;
				case WORK:
					System.out.print("Work phone #: ");
					break;
					}      
					System.out.println(phoneNumber.getNumber());
				}
			}
		}
	
	public static void main(String[] args) throws Exception {
//		if (args.length != 1) {
//			System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE"); 
//			System.exit(-1);
//		} 
		// 读取已经存在.book文件
		AddressBook addressBook = AddressBook.parseFrom(new FileInputStream("src/Book/TestPerson.book"));
		Print(addressBook);
	}
 }