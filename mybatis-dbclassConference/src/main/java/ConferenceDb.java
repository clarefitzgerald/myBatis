/* COSC 4800 ASSIGNMENT 6
 * Demonstrate connection between database and application.
 * @AUTHOR CLARE FITZGERALD
 * DATE: 12/6/2023
 * INSTRUCTOR 4800 DR. MADIRAJU
 * 
 */
import java.util.ArrayList;
import java.util.List;

import com.domain.Author;
import com.domain.Paper;
import com.domain.Participator;
import com.service.ParticipatorService;
import java.util.Scanner;
public class ConferenceDb {

	public static void main(String[] args) {
		
		int choice = 0;
		while (choice!=4) {
			System.out.println("MENU\n\n****************************************************\n\n"
				+ "(1) Print the list of papers whose average overall Recommendation is more than 7\n\n"
				+ "(2) Enter Participator Details\n\n"
				+ "(3) Enter Author Details\n\n"
				+ "(4) Exit\n\n"
				+ "****************************************************\n\n");
		System.out.print("Please make your choice: ");
		Scanner a = new Scanner(System.in);
		choice = a.nextInt();
		/*
		 * Switch Statements below follow the guidelines specified. 
		 * Case 1 calls the getGoodPapers methods from ParticipatorService which then calls
		 * 		getGoodPapers in ParticipatorDAO, which then starts a connection factory to get selectGoodPapers
		 * 		
		 * Case 2 and 3 have helper methods at the bottom.
		 * 
		 * Case 4 just exits the while loop.
		 * 
		 * Default case displays a hardcoded "error" message reminding the user which values are valid.
		 */
		switch(choice) {
			case 1: //Print list of >7 papers
				System.out.println("The list of papers whose average overall recommendation value is more than 7 is :");
				List<Paper> paperList = new ArrayList<Paper>();
				ParticipatorService parService = new ParticipatorService();
				paperList = parService.getGoodPapers();
			//	System.out.print(paperList);
				for(Paper p : paperList) 
					System.out.println(p.getTitle()+ " (" + p.getPaperid()+ ")\n");
				break;
			case 2: //Participator Deetz
				insertParticipant();
				break;
			case 3: //Print all authors + emails and add new one
				insertAuthor();
				break;
			case 4: //Exit
				return;
			default: 
				System.out.print("Invalid input. Entry must be 1, 2, 3, or 4.");
			}
		
		}
		return;
	}
		/*
		 * This method is really long just because of how many prompts there are. 
		 * It collects the information it needs in order to call the Participator constructor (with parameters).
		 * Once the object is created, it passes it into ParticipantService and follows the same path as Case 1 
		 * but using different methods.
		 * 
		 */
		public static void insertParticipant() {
			try {
			System.out.print("Please enter Email : ");
				Scanner a = new Scanner(System.in);
				String email = a.nextLine();
				//System.out.println(email);
				List<Participator> parList = new ArrayList<Participator>();
				ParticipatorService parService = new ParticipatorService();
				parList = parService.getParticipatorList();
				//Checking for Primary Key Error
				for(Participator p : parList) {
					if (email.equals(p.getEmail())) {
						System.out.println("Email must be unique. This email is already used.");
						System.out.println("Insert unsuccessful, please try again.");
						return; }
				}
			System.out.print("Please enter First Name : ");
				a = new Scanner(System.in);
				String fname = a.nextLine();
				//System.out.println(fname);
			System.out.print( "Please enter Last Name : ");
				a = new Scanner(System.in);
				String lname = a.nextLine();
				//System.out.println(lname);
			System.out.print("Please enter Minit : ");
				a = new Scanner(System.in);
				String minit = a.nextLine();
				//System.out.println(minit);
			System.out.print("Please enter Phone : ");
				a = new Scanner(System.in);
				String phone = a.nextLine();
				//System.out.println(phone);
			System.out.print("Please enter Affiliation : ");
				a = new Scanner(System.in);
				String affiliation = a.nextLine();
				//System.out.println(affiliation);
			Participator p = new Participator(email, fname, minit, lname, phone, affiliation);
			parService.insertParticipator(p);	
			System.out.print("Participator Record inserted successfully\n");
				return;	
			}
			catch(Exception e) {
				System.out.println("Insert unsuccessful, please try again.");
				System.out.println(e.getMessage()); //print the stack trace to give user an idea of what went wrong
				return;
			}
		}
		/*
		 * Shorter than Case 2 because Author only has 1 field.
		 * If the ConferenceMapper runs the SQL code and it finds a foreign key error 
		 * (there is no participant with that email), that is included in the getMessage.
		 */
		public static void insertAuthor() {
			/*
			System.out.println("Participators along with their Email Addresses  are :\n");
			List<Author> authList = new ArrayList<Author>();
			ParticipatorService authService = new ParticipatorService();
			authList = authService.getAuthorList();
			for(Author au : authList) {
				System.out.println(au.getFname()+ " ("+ au.getEmail()+ ")\n"); }
			*/
			List<Participator> parList = new ArrayList<Participator>();
			ParticipatorService parService = new ParticipatorService();
			parList = parService.getParticipatorList();
			//Checking for Primary Key Error
				for (Participator p: parList){
					System.out.println(p.getFname()+ " ("+ p.getEmail()+ ")\n"); }


			try {
				parService = new ParticipatorService();
				System.out.print("Please enter a Author for the Conference : ");
				Scanner a = new Scanner(System.in);
				String email = a.nextLine();
				Author au = new Author(email);
				parService.insertAuthor(au);
				System.out.println("Inserted Author Record Successfully");
				return; 
				}		
			catch(Exception e) {
				System.out.println("Insert unsuccessful, please try again.");
				System.out.println(e.getMessage());
				return;
			}
			}
	}


		/*
	*These are just copies of the ConferenceMapper SQL so I could refer back to the fields
	*
	<mapper namespace="com.mapper.ConferenceMapper">
	<resultMap id="ParticipatorResult" type="Participator">
		<id property="email" column="email" />
		<result property="fname" column="fname" />
		<result property="lname" column="lname" />
		<result property="minit" column="minit" />
		<result property="phone" column="phone" />
		<result property="affiliation" column="affiliation" />
	</resultMap>
	<select id="selectAll" resultMap="ParticipatorResult">
		SELECT * FROM participator
	</select>
	
	<select id = "selectGoodPapers" resultType = "Paper">
		select p.paperid, p.title, p.filename, p.contactauthoremail, p.abstract
		from reviews r, paper p
		where r.paperid = p.paperid
		group by r.paperid, p.paperid, p.title, p.filename, p.contactauthoremail, p.abstract
		having avg(r.overallrecomm)> 7
		order by r.paperid asc
	</select>

	<insert id="insertParticipator" parameterType="com.domain.Participator">
		INSERT INTO participator(email, firstname, lastname, minit, phone, affiliation) VALUES (#{email},#{fname},#{lname},#{minit},#{phone},#{affiliation})
	</insert>
	
	<insert id="insertAuthor" parameterType="com.domain.Author">
		INSERT INTO author(email) VALUES (#{email})
	</insert>

</mapper>
	*/


