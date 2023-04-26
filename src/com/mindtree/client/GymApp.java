package com.mindtree.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.mindtree.entity.Gym;
import com.mindtree.entity.Member;
import com.mindtree.entity.NameCompare;
import com.mindtree.exception.GymAppException;
import com.mindtree.exception.GymAppServiceException;
import com.mindtree.service.GymService;
import com.mindtree.service.MemberService;
import com.mindtree.service.impl.GymServiceImpl;
import com.mindtree.service.impl.MemberServiceImpl;

public class GymApp {
	static Scanner scanner = new Scanner(System.in);
	static MemberService memberService = new MemberServiceImpl();
	static GymService gymService = new GymServiceImpl();

	public static void main(String[] args) {
		byte choice = 0;
		do {
			System.out.println("Enter the choice:");
			System.out.println("1. Add Members to the gym");
			System.out.println("2. Get all  members of particular gym ");
			System.out.println("3. Update height of a member by member id");
			System.out.println("4. Sort members by name");
			System.out.println("5. Get all members along with Gym details whose age is gretaer than given value");
			System.out.println("6. Exit");
			choice = scanner.nextByte();
			try {
				menu(choice);
			} catch (GymAppException e) {
				System.out.println(e.getMessage());
			}
		} while (choice != 6);

	}

	private static void menu(int choice) throws GymAppException {

		switch (choice) {
		case 1:
			System.out.println("Enter the id of Gym");
			byte id = scanner.nextByte();
			Gym gym = null;
			try {
				gym = gymService.getGymByID(id);
			} catch (GymAppServiceException e1) {
				System.out.println(e1.getMessage());
			}
			Set<Member> members = assignsMembersToGym(gym);
			try {
				members = memberService.insertMembers(members);
				displayMembers(members);
			} catch (GymAppServiceException e) {
				System.out.println(e.getMessage());
			}

			break;
		case 2:
			System.out.println("Enter the Gym id");
			id = scanner.nextByte();
			try {
				members = memberService.getMembersByGymID(id);
				displayMembers(members);
			} catch (GymAppServiceException e) {
				//e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			System.out.println("Enter the Member id");
			id = scanner.nextByte();
			System.out.println("Enter the height which you want to update");
			float height = scanner.nextFloat();
			String string = "";
			try {
				string = memberService.updateMemberHeightByID(id, height);
			} catch (GymAppServiceException e) {
				System.out.println(e.getMessage());
			}
			System.out.println(string);
			break;
		case 4:

			try {
				List<Member> list = memberService.getAllMembers();
				sortTheMembers(list);
			} catch (GymAppServiceException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 5:
			System.out.println("Enter the age");
			int age = scanner.nextInt();
			try {
				List<Member> list = memberService.ListAllDetailsUsingage(age);
				listMembers(list);
			} catch (GymAppServiceException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Enter the correct options");
		}
	}

	private static void listMembers(List<Member> list) {
		try {
			writeIntoExcel(list);
			ReadFromExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void ReadFromExcel() {
		String path = "D:\\FileHandling\\gym.xls";
		try {
			FileInputStream file = new FileInputStream(new File(path));
			Workbook book = new HSSFWorkbook(file);
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Sheet> sheets = book.sheetIterator();
			while (sheets.hasNext()) {
				Sheet sh = sheets.next();
				System.out.println("Sheet name is " + sh.getSheetName());
				System.out.println("---------");
				Iterator<Row> iterator = sh.iterator();
				while (iterator.hasNext()) {
					Row row = iterator.next();
					Iterator<Cell> cellIterator = row.iterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						String cellValue = dataFormatter.formatCellValue(cell);
						System.out.print(cellValue + "\t");
					}
					System.out.println();
				}
			}
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void writeIntoExcel(List<Member> list) throws Exception {
		String path = "D:\\FileHandling\\gym.xls";
		try {
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet("GyApp");
		String[] coloumnHeading = { "MemberName", "Age", "Gender", "height", "GymName" };//Heading cell names in the excel
		Row headerRow = sheet.createRow(0);
		for (int i = 0; i < coloumnHeading.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(coloumnHeading[i]);
		}

		sheet.createFreezePane(0, 1);
		int rownum = 1;
		for (Member tempMembers : list) {
			Row row = sheet.createRow(rownum++);
			row.createCell(0).setCellValue(tempMembers.getName());
			row.createCell(1).setCellValue(tempMembers.getAge());
			row.createCell(2).setCellValue(tempMembers.getGender());
			row.createCell(3).setCellValue(tempMembers.getHeight());

			row.createCell(4).setCellValue(tempMembers.getGym().getName());
			FileOutputStream fileOutput = new FileOutputStream(path);
			book.write(fileOutput);
		}

		for (int i = 0; i < coloumnHeading.length; i++) {
			sheet.autoSizeColumn(i);
		}
		book.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sortTheMembers(List<Member> list) {

		NameCompare nameCompare = new NameCompare();
		Collections.sort(list, nameCompare);
		for (Member member : list) {
			System.out.println(member.getName() + " : " + member.getAge() + " : " + member.getGender() + " : "
					+ member.getHeight());
		}
	}

	private static void displayMembers(Set<Member> members) {
		writeDatasIntoFile(members);
		readFile();

		System.out.println("------------------------------------------------------------------------------");
		System.out.println();
		try {
			writeToFile(members);// serilaization
		} catch (GymAppException e) {
			e.printStackTrace();
		}
		Set<Member> tempDesigns = getFromFile();// deserialization
		for (Member member : tempDesigns) {
			System.out.println(member.getName() + " : " + member.getAge() + " : " + member.getGender() + " : "
					+ member.getHeight());
		}
	}

	private static Set<Member> getFromFile() {

		Set<Member> members = null;
		try {
			FileInputStream fileInput = new FileInputStream("D:\\FileHandling\\gym.txt");
			BufferedInputStream read = new BufferedInputStream(fileInput);
			ObjectInputStream objectInput = new ObjectInputStream(read);
			members = (Set<Member>) objectInput.readObject();
			objectInput.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return members;

	}

	private static void writeToFile(Set<Member> members) throws GymAppException {
		try {
			File file = new File("D:\\FileHandling\\gym.txt");
			FileOutputStream fileOutput = new FileOutputStream(file);
			BufferedOutputStream outputStream = new BufferedOutputStream(fileOutput);
			ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);

			objectOutput.writeObject(members);
			objectOutput.flush();
			objectOutput.close();

		} catch (IOException e) {
			throw new GymAppException();
		}
	}

	private static void readFile() {
		File obj = new File("D:\\FileHandling\\gym.txt");
		try {
			Scanner read = new Scanner(obj);
			while (read.hasNextLine()) {
				String data = read.nextLine();
				System.out.println(data);
			}
			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static void writeDatasIntoFile(Set<Member> members) {
		try {
			FileWriter writer = new FileWriter("D:\\FileHandling\\gym.txt");
			BufferedWriter buffWriter = new BufferedWriter(writer);
			for (Member tempMembers : members) {
				String string = "";
				string = string + tempMembers.getName() + " : " + tempMembers.getAge() + " : " + tempMembers.getGender()
						+ " : " + tempMembers.getHeight();

				buffWriter.write(string);
				buffWriter.newLine();
			}
			buffWriter.close();
			writer.close();

		} catch (IOException e) {
			System.out.println("An error occured");
			e.printStackTrace();
		}
	}

	private static Set<Member> assignsMembersToGym(Gym gym) {

		Set<Member> members = new LinkedHashSet<Member>();
		System.out.println("Enter the how many members has to be inserted to this Gym");
		byte number = scanner.nextByte();
		Member[] tempMember = new Member[number];
		for (int i = 0; i < tempMember.length; i++) {
			System.out.println("Enter the member Name:");
			scanner.nextLine();
			String name = scanner.nextLine();
			System.out.println("Enter the member age:");
			int age = scanner.nextInt();
			System.out.println("Enter the member gender:");
			scanner.nextLine();
			String gender = scanner.nextLine();

			System.out.println("Enter the height:");
			float height = scanner.nextFloat();

			tempMember[i] = new Member(name, age, gender, height, gym);
			members.add(tempMember[i]);
		}

		return members;

	}

}
