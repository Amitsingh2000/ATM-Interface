import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ATM 
{
	static Scanner sc=new Scanner(System.in);
	
	public static Map<String,User> userList=new HashMap<>();
	
	private static void register()
	{
		System.out.println("---------- Register User ----------");
		User user=new User();
		System.out.println("Enter User Id - ");
		user.setUserId(sc.next());
		System.out.println("Enter User Password - ");
		user.setPassword(sc.next());
		System.out.println("Enter User Account No -");
		user.setAccountNo(sc.nextLong());
		user.setAccountBalance(0);
		userList.put(user.getUserId(), user);
		System.out.println("----- User Register Successfully -----");
	}
	private static void login() 
	{
		System.out.println("---------- User Login ----------");
		System.out.println("Enter User Id");
		String userId =sc.next();
		User user = userList.get(userId);
		if(user!=null)
		{
			System.out.println("Enter User Password - ");
			String userPassword =sc.next();
			if(user.getPassword().equals(userPassword))
			{
				List<String> userTransctions;
				if(user.getTransactions()!=null)
				{
					userTransctions = user.getTransactions();
				}
				else
				{
					userTransctions=new ArrayList<>();
				}
				log :while(true)
				{
					System.out.println("1.For Deposite \n"
							+ "2.For Withdraw \n"
							+ "3.For Check Balance \n"
							+ "4.For Transfer \n"
							+ "5.For Transaction History \n"
							+ "6.For Logout \n"
							+ "Enter Choice - ");
					int ch =sc.nextInt();
					switch(ch)
					{
						case 1:
							System.out.println("Enter Deposite Amount - ");
							double depoAmt =sc.nextDouble();
							deposite(user,depoAmt);
							userTransctions.add(depoAmt+" Amount Deposited Successfully");
							break;
						case 2:
							System.out.println("Enter Withdraw Amount - ");
							double withAmt=sc.nextDouble();
							if(withdraw(user,withAmt))
							{
								userTransctions.add(withAmt+" Amount Withdraw Successfully");
							}
							break;
						case 3:
							checkBalance(user);
							break;
						case 4:
							System.out.println("Enter Transfer Amount - ");
							double tranAmt =sc.nextDouble();
							System.out.println("Enter recipient User Id - ");
							String to =sc.next();
							if(transfer(to,user,tranAmt))
							{
								userTransctions.add(tranAmt+" Amount Transfer Successfully To "+to);
							}
							break;
						case 5:
							history(userTransctions);
							break;
						case 6:
							System.out.println("User Logout Successfully");
							user.setTransactions(userTransctions);
							break log;
						default:
							System.out.println("Enter Valid Login");
							break;
					}
					
				}
			}
			else
			{
				System.out.println("Enter valid Credentials");
			}
		}
		else
		{
			System.out.println("User Not Found");
		}
	}
	
	
	private static void history(List<String> userTransctions) 
	{
		if(userTransctions != null)
		{
			userTransctions.forEach((t)->
			{
				System.out.println(t);
			});
		}
		else
		{
			System.out.println("No Transaction Found In Your Account ");
		}
	}
	private static boolean transfer(String to,User user, double tranAmt) 
	{
		if(tranAmt<=user.getAccountBalance())
		{
			User recipient =userList.get(to);
			if(recipient!=null && recipient.getUserId()!=user.getUserId())
			{
				user.setAccountBalance(user.getAccountBalance()-tranAmt);
				recipient.setAccountBalance(recipient.getAccountBalance()+tranAmt);
				List<String> recipientTrans =recipient.getTransactions();
				recipientTrans.add(tranAmt+" Amount recived From "+user.getUserId());
				System.out.println(tranAmt+" Amount Transfer Successfully To "+recipient.getAccountNo()+" Account");
				
			}
			else
			{
				System.out.println("Recipient Not Found In Database");
			}
			return true;
		}
		else
		{
			System.out.println("Insufficent balance in your Account ");
			return false;
		}
		
	}
	private static boolean withdraw(User user, double withAmt)
	{
		if(withAmt<=user.getAccountBalance())
		{
			user.setAccountBalance(user.getAccountBalance()-withAmt);
			System.out.println(withAmt+" Amount Withdraw Successfully From Your Account");
			return true;
		}
		else
		{
			System.out.println(" Insufficent balance in your Account ");
			return false;
		}
		
	}
	private static void checkBalance(User user) 
	{
		System.out.println(" Your Account Balance is  - "+user.getAccountBalance());
	}
	private static void deposite(User user,double depoAmt) 
	{
		user.setAccountBalance(user.getAccountBalance()+depoAmt);
		System.out.println(depoAmt+" Amount Deposited Successfully");
	}
	public static void main(String[] args)
	{
		while(true)
		{
			System.out.println("---------- ATM Interface ----------");
			System.out.println("1. For Register User \n"
					+ "2. For Login User \n"
					+ "3. For Exit \n"
					+ "Enter Choice - ");
			int ch =sc.nextInt();
			switch(ch)
			{
				case 1:
					register();
					break;
				case 2:
					login();
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Enter Valid Choice");
					break;
			}
		}
		
	}

}
