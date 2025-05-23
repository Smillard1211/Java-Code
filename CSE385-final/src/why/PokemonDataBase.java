package why;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sql.DataSource;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.cj.jdbc.MysqlDataSource; 







public class PokemonDataBase {
	
	public static int pokeNum = 0;
	
	//these hold the different details needed for later
	public static List<String> poke = new LinkedList<>();
	public static List<Integer> hpList = new LinkedList<>();
	public static List<Integer> atkList = new LinkedList<>();
	public static List<Integer> defList = new LinkedList<>();
	public static List<Integer> spAList = new LinkedList<>();
	public static List<Integer> spDList = new LinkedList<>();
	public static List<Integer> speList = new LinkedList<>();
	public static Map<String, String[]> natureList = new HashMap<>();
	
	//should hold the team that would be used later
	public static Map<String, int[]> team = new HashMap<>();
	public static Map<String, Integer> types = new HashMap<>();
	public static String[] pokemonArr = new String[6];
	
	//textfields that should be used for later
	public static JTextField hpField;
	public static JTextField defField;
	public static JTextField atkField;
	public static JTextField spAField;
	public static JTextField spDField;
	public static JTextField speField;
	public static JTextField pokemonName;
	public static JTextField abilityName;
	public static JTextField strongTime;
	public static JTextField defendTime;
	
	//different comboBox(aka dropdown menus)
	public static JComboBox hpCom;
	public static JComboBox defCom;
	public static JComboBox atkCom;
	public static JComboBox spACom;
	public static JComboBox spDCom;
	public static JComboBox speCom;
	
	
	
	
	public static JFrame newframe; // shows the details for the different pokemon
	public static JFrame frame; //the main frame
	public static JFrame abilityframe; //is the adbility description page
	public static JFrame analysisTime; //as the analysis data
	
	public static DataSource getSQLDataSource() {
		FileInputStream fis = null;
		MysqlDataSource mysqlDS = null; 
		Properties props = new Properties();
		
		try {
			
			
			fis = new FileInputStream("db.properties");
			
			
			//setting properties of the DataSource class
			props.load(fis);
			mysqlDS = new MysqlDataSource();
			mysqlDS.setURL(props.getProperty("JDBC_URL"));
			mysqlDS.setUser(props.getProperty("JDBC_USERNAME"));
			mysqlDS.setPassword(props.getProperty("JDBC_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mysqlDS;
	}//end getMySQLDataSource
    
	private static void addPokemonTypes() {
		
		for(int i = 0; i < pokeNum; i++) {
			for (Map.Entry<String,int[]> entry : team.entrySet()) {
				List<String> tmp = getType(entry.getKey());
				getTypeForMap(tmp.get(0), tmp.get(1));
					
			}	
		}
	}
	
	
	public static void removeFromArr(String why) {
		
		
		for(int i = 0; i <pokeNum; i++) {
			if(pokemonArr[i].equals(why)) {
				pokemonArr[i] = pokemonArr[pokeNum-1];
				pokemonArr[pokeNum-1] = null;
				return;
			}
			
			
		}
		
		
	}
	
	public static Boolean doesHave(String cry) {
		
		for(int i = 0; i <pokeNum; i++) {
			
			if(cry.equals(pokemonArr[i])) return true;
			
		}
		
		return false;
	}
	
	
	private static String checkVal(String input)
    {
        try {
            Integer.valueOf(input);
            return "Integer";
        }
        catch (NumberFormatException e) {
            return "String";
        }
    }
	
	public static void setUpTeam(String pokemon) {
		pokemonArr[pokeNum] = pokemon;
		int index = poke.indexOf(pokemon);
		int[] val = {hpList.get(index), atkList.get(index), defList.get(index), spAList.get(index), spDList.get(index), speList.get(index)};
		
		
		team.put(pokemon, val);
		pokeNum++;
		
		
		
	}
	
	public static void getTypeForMap(String type1, String type2) {
		 
		String[] data = {"Normal", "Fire", "Water", "Electric", "Grass",
                "Ice", "Fighting", "Poison", "Ground", "Flying",
                "Psychic", "Bug", "Rock", "Ghost", "Dragon",
                "Dark", "Steel", "Fairy"};
			
			DataSource dataSource = null; //instance of the DataSource class
			
			
			
			dataSource = getSQLDataSource();
			
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				connection = dataSource.getConnection();
				statement = connection.createStatement();
				resultSet = statement.executeQuery("select * from pokemondata.typechart where defensetype1 like '"+ type1 + "' and defensetype2 like '"+ type2 + "';");
				
				while(resultSet.next()){
					for(int i = 0; i < data.length; i++) {
						types.put(data[i], types.get(data[i]) + resultSet.getInt(data[i]));
						
					}
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(resultSet != null) {
						resultSet.close();
					}//end if
					if(statement != null) {
						statement.close();
					}//end if
					if(connection != null) {
						connection.close();
					}//end if			
				}//end try
				catch(Exception e) {
					e.printStackTrace();
				}//end catch	
			}//end finally
		
		
	}
	
	private static void addToMap() {
		String[] data = {"Normal", "Fire", "Water", "Electric", "Grass",
                "Ice", "Fighting", "Poison", "Ground", "Flying",
                "Psychic", "Bug", "Rock", "Ghost", "Dragon",
                "Dark", "Steel", "Fairy"};
		
		for(int i = 0; i<data.length; i++) {
			types.put(data[i], 0);
			
			
		}
		
		
	}

	//shows new window on 
	public static void createFrame(String pokemon) {
		//some basic code
		
		String[] tmp = natureList.get(pokemon);
		List<String> type = getType(pokemon);
		
		
		
		newframe = new JFrame();
		newframe.setBounds(100, 100, 700, 550);
		newframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newframe.getContentPane().setLayout(new BoxLayout(newframe.getContentPane(), BoxLayout.X_AXIS));		
		
		JDesktopPane desktopPane = new JDesktopPane();
		newframe.getContentPane().add(desktopPane);
		
		pokemonName = new JTextField();
		pokemonName.setText(pokemon);
		pokemonName.setEditable(false);
		pokemonName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pokemonName.setBounds(174, 63, 312, 55);
		pokemonName.setHorizontalAlignment(SwingConstants.CENTER);
		desktopPane.add(pokemonName);
		pokemonName.setColumns(10);
		
		JButton ablility1 = new JButton(tmp[0]);
		ablility1.setBounds(67, 332, 130, 55);
		ablility1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ablility1.getText().equals(""));
				else ablilityFrame(ablility1.getText());
				
			}
		});
		desktopPane.add(ablility1);
		
		JButton ablility2 = new JButton(tmp[1]);
		ablility2.setBounds(258, 332, 130, 55);
		ablility2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ablility2.getText().equals(""));
				else ablilityFrame(ablility2.getText());
				
			}
		});
		desktopPane.add(ablility2);
		
		JButton hidden = new JButton(tmp[2]);
		hidden.setBounds(459, 332, 130, 55);
		hidden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hidden.getText().equals(""));
				else ablilityFrame(hidden.getText());
				
			}
		});
		desktopPane.add(hidden);
		
		JLabel random = new JLabel("Add?");
		random.setFont(new Font("Tahoma", Font.PLAIN, 22));
		random.setBounds(297, 165, 48, 41);
		desktopPane.add(random);
		
		JButton yesButton = new JButton("Yes");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUpTeam(pokemon);
				newframe.dispatchEvent(new WindowEvent(newframe, WindowEvent.WINDOW_CLOSING));
				
			}
		});
		yesButton.setBounds(219, 217, 91, 23);
		desktopPane.add(yesButton);
		
		
		JButton noButton = new JButton("No");
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				newframe.dispatchEvent(new WindowEvent(newframe, WindowEvent.WINDOW_CLOSING));
			}
		});
		noButton.setBounds(320, 217, 91, 23);
		desktopPane.add(noButton);
		
		JLabel lblAbilities = new JLabel("Abilities");
		lblAbilities.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbilities.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAbilities.setBounds(229, 283, 171, 41);
		desktopPane.add(lblAbilities);
		
		JLabel type1Time = new JLabel("Type 1: " + type.get(0));
		type1Time.setFont(new Font("Tahoma", Font.PLAIN, 22));
		type1Time.setBounds(184, 121, 250, 41);
		desktopPane.add(type1Time);
		
		JLabel type2Time = new JLabel("Type 2: " + type.get(1));
		type2Time.setFont(new Font("Tahoma", Font.PLAIN, 22));
		type2Time.setBounds(402, 121, 150, 41);
		desktopPane.add(type2Time);
		
		newframe.setVisible(true);
	
		
	
	}
	
	public static void removePokemonTime(JPanel pane) {
		int test = 5;
		
		for(int i = 0; i < pokeNum; i++) {
			int[] tmp = team.get(pokemonArr[i]);
			
		
		JButton button = new JButton(pokemonArr[i]);
        button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeFromArr(button.getText());
				if(doesHave(button.getText())) {}
				else team.remove(button.getText());
				button.setText("removed");
				pokeNum--;
				button.setEnabled(false);
			}});
			button.setBounds(15, test, 100, 20);
			test += 25;
			JLabel temp = new JLabel("HP:" + tmp[0] + " | Atk:" + tmp[1] + " | Def:" + tmp[2] + " | SpA:" + tmp[3] + " | SpD:" + tmp[4] + " | Spe:" + tmp[5]);
			temp.setBounds(130, test, 50, 20);
       
			
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            buttonPanel.add(button);
            buttonPanel.add(temp);

            pane.add(buttonPanel);
     
		}
		
		
		
	}
	
	public static void analysisTime() {
		
		addPokemonTypes();
		
		String strong = Collections.min(types.entrySet(), Map.Entry.comparingByValue()).getKey();;
		
		String defend = Collections.max(types.entrySet(), Map.Entry.comparingByValue()).getKey();;
		
		
		
		analysisTime = new JFrame();
		analysisTime.setBounds(100, 100, 700, 550);
		analysisTime.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		analysisTime.getContentPane().setLayout(new BoxLayout(analysisTime.getContentPane(), BoxLayout.X_AXIS));		
		
		JDesktopPane desktopPane = new JDesktopPane();
		analysisTime.getContentPane().add(desktopPane);
		
		JLabel lblNewLabel = new JLabel("Defend Against");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(38, 38, 150, 35);
		desktopPane.add(lblNewLabel);
		
		defendTime = new JTextField();
		defendTime.setEditable(false);
		defendTime.setText(defend);
		defendTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		defendTime.setBounds(185, 31, 190, 50);
		
		desktopPane.add(defendTime);
		defendTime.setColumns(10);
		
		JLabel lblStrongAgainst = new JLabel("Strong Against");
		lblStrongAgainst.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblStrongAgainst.setBounds(38, 107, 150, 35);
		desktopPane.add(lblStrongAgainst);
		
		strongTime = new JTextField();
		strongTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		strongTime.setEditable(false);
		strongTime.setText(strong);
		strongTime.setColumns(10);
		strongTime.setBounds(185, 100, 190, 50);
		desktopPane.add(strongTime);
		
		JPanel scrol = new JPanel();
        scrol.setLayout(new GridLayout(0, 1));
		
        JScrollPane another = new JScrollPane(scrol);
		another.setBounds(89, 166, 469, 215);
		desktopPane.add(another);
		int test = 5;
		for(int i = 0; i < pokeNum; i++) {
			int[] tmp = team.get(pokemonArr[i]);
			Map<String, Integer> help = new HashMap<>();
			
			help.put("hp", tmp[0]);
			help.put("atk", tmp[1]);
			help.put("def", tmp[2]);
			help.put("spA", tmp[3]);
			help.put("spD", tmp[4]);
			help.put("spe", tmp[5]);
			
			//max bit
			String max = Collections.max(help.entrySet(), Map.Entry.comparingByValue()).getKey();
			String min = Collections.min(help.entrySet(), Map.Entry.comparingByValue()).getKey();
			
    			test += 25;
    			JLabel temp = new JLabel(pokemonArr[i] + " | max: " + max + ": " + help.get(max)  + " | min: " + min + ": " + help.get(min));
    			temp.setBounds(130, test, 50, 20);
			
    			JPanel buttonTime = new JPanel(new FlowLayout(FlowLayout.LEFT));
                buttonTime.add(temp);
    			
    			scrol.add(buttonTime);
		}
		
		
		analysisTime.setVisible(true);
		
	}
	
	
	
	public static void ablilityFrame(String ability) {
		abilityframe = new JFrame();
		abilityframe.setBounds(100, 100, 700, 550);
		abilityframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		abilityframe.getContentPane().setLayout(new BoxLayout(abilityframe.getContentPane(), BoxLayout.X_AXIS));		
		
		JDesktopPane desktopPane = new JDesktopPane();
		abilityframe.getContentPane().add(desktopPane);
		
		abilityName = new JTextField();
		abilityName.setEditable(false);
		abilityName.setText(ability);
		abilityName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		abilityName.setBounds(188, 58, 252, 52);
		desktopPane.add(abilityName);
		abilityName.setColumns(10);
		
		JTextArea DescriptionTime = new JTextArea();
		DescriptionTime.setFont(new Font("Monospaced", Font.PLAIN, 20));
		DescriptionTime.setEditable(false);
		DescriptionTime.setText(getDescription(ability));
		DescriptionTime.setBounds(162, 162, 350, 196);
		DescriptionTime.setLineWrap(true);
		desktopPane.add(DescriptionTime);
		
		JButton closeTime = new JButton("Close");
		closeTime.setBounds(262, 376, 91, 23);
		closeTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				abilityframe.dispatchEvent(new WindowEvent(abilityframe, WindowEvent.WINDOW_CLOSING));
			}
		});
		desktopPane.add(closeTime);
		
		
		abilityframe.setVisible(true);
	}
	
	public static void addButtons(JPanel pane) {
		int test = 5;
		if(poke.isEmpty()) {
			JLabel temp = new JLabel("No Pokemon exist with that criteria");
			temp.setFont(new Font("Tahoma", Font.PLAIN, 20));
			temp.setBounds(130, test, 50, 20);
			
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			
			buttonPanel.add(temp);

            pane.add(buttonPanel);
            return;
		}
				
		
		for (int i = 0; i < poke.size(); i++) {
            JButton button = new JButton(poke.get(i));
            button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				if(pokeNum == 6) return;
    				createFrame(button.getText());
    			}});
    			button.setBounds(15, test, 100, 20);
    			test += 25;
    			JLabel temp = new JLabel("HP:" + hpList.get(i) + " | Atk:" + atkList.get(i) + " | Def:" + defList.get(i) + " | SpA:" + spAList.get(i) + " | SpD:" + spDList.get(i) + " | Spe:" + speList.get(i));
    			temp.setBounds(130, test, 50, 20);
           
    			
    			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                buttonPanel.add(button);
                buttonPanel.add(temp);

                pane.add(buttonPanel);
            
        }
		
		
		
	}
	
	private static String getQuery(String type1, String type2, int hp, int atk, int def, int spA, int spD, int spe) {
		String ret = "Select * from pokemondata.pokemon";
		
		//returns a query that gets every pokemon
		if(type1 == null && type2 == null && hp == -1 && atk == -1 && def == -1 && spA == -1 && spD == -1 && spe == -1) return ret;
		else ret += " where ";
		
		//used to add the X amount of ands for the query
		int test = 0;
		
		if(type1 != null) test++;
		if(type2 != null) test++;
		if(hp != -1) test++;
		if(atk != -1) test++;
		if(def != -1) test++;
		if(spA != -1) test++;
		if(spD != -1) test++;
		if(spe != -1) test++;
		
		
		
		if(type1 != null) { 
			ret += "(type1 like \""+ type1+"\" or type2 like \""+type1+ "\")";//adds the first type
			test--;
			if(test != 0) ret+= " and ";
		
		}
		if(type2 != null) {
			ret += "(type1 like \""+ type2+"\" or type2 like \""+type2+ "\")"; //adds the second type
			test--;
			if(test != 0) ret+= " and ";
		}
		
		if(hp != -1) {
			ret += "(hp "+ (String)hpCom.getSelectedItem()+ " " + hp + ")"; //adds the hp stat
			test--;
			if(test != 0) ret+= " and ";
			
		}
		
		if(atk != -1) {
			ret += "(atk "+ (String)atkCom.getSelectedItem()+ " " + atk + ")"; //adds the atk stat
			test--;
			if(test != 0) ret+= " and ";
		}
		
		if(def != -1) {
			ret += "(def "+ (String)defCom.getSelectedItem()+ " " + def + ")"; //adds the def stat
			test--;
			if(test != 0) ret+= " and ";
		}
		
		if(spA != -1) {
			ret += "(spA "+ (String)spACom.getSelectedItem()+ " " + spA + ")"; //adds the spA stat
			test--;
			if(test != 0) ret+= " and ";
		}
		
		if(spD != -1) {
			ret += "(spD "+ (String)spDCom.getSelectedItem()+ " " + spD + ")"; //adds the spD stat
			test--;
			if(test != 0) ret+= " and ";
		}
		
		if(spe != -1) {
			ret += "(spe "+ (String)speCom.getSelectedItem()+ " " + spe + ")"; //adds the spe stat
			
		}
		
		
		return ret;
	}
	//grabs the pokemon type of whatever pokemon you would need by giving the name of that pokemon
	private static List<String> getType(String pokemon){
		DataSource dataSource = null;
		dataSource = getSQLDataSource();
		List<String> ret = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from pokemondata.pokemon where pokemon like '"+ pokemon + "';");
			
			while(resultSet.next()){
				ret.add(resultSet.getString("Type1"));
				ret.add(resultSet.getString("type2"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) {
					resultSet.close();
				}//end if
				if(statement != null) {
					statement.close();
				}//end if
				if(connection != null) {
					connection.close();
				}//end if			
			}//end try
			catch(Exception e) {
				e.printStackTrace();
			}//end catch	
		}//end finally
		
		return ret;
	}
		
		
		
	
	private static void getPokemonFromType(String type1, String type2, int hp, int atk, int def, int spA, int spD, int spe) {
		DataSource dataSource = null; //instance of the DataSource class
		dataSource = getSQLDataSource();
		
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getQuery(type1, type2, hp, atk, def, spA, spD, spe));
			while(resultSet.next()){
				String name = resultSet.getString("pokemon");
				poke.add(name);
				hpList.add(resultSet.getInt("Hp"));
				defList.add(resultSet.getInt("Def"));
				atkList.add(resultSet.getInt("Atk"));
				spAList.add(resultSet.getInt("SpA"));
				spDList.add(resultSet.getInt("SpD"));
				speList.add(resultSet.getInt("Spe"));
				String[] tmp = {resultSet.getString("Ability1"), resultSet.getString("Ability2"), resultSet.getString("HiddenAbility")};
				natureList.put(name, tmp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) {
					resultSet.close();
				}//end if
				if(statement != null) {
					statement.close();
				}//end if
				if(connection != null) {
					connection.close();
				}//end if			
			}//end try
			catch(Exception e) {
				e.printStackTrace();
			}//end catch	
		}//end finally
	}//end testDataSource()
	
	
	private static String getDescription(String Ability) {
		DataSource dataSource = null; //instance of the DataSource class
		dataSource = getSQLDataSource();
		String ret = "";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from pokemondata.abilities where name like '"+ Ability + "';");
			
			while(resultSet.next()){
				ret = resultSet.getString("description");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(resultSet != null) {
					resultSet.close();
				}//end if
				if(statement != null) {
					statement.close();
				}//end if
				if(connection != null) {
					connection.close();
				}//end if			
			}//end try
			catch(Exception e) {
				e.printStackTrace();
			}//end catch	
		}//end finally
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane);
		
		JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1));
		
        JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setBounds(89, 166, 469, 215);
		desktopPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Type 1");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(34, 44, 63, 23);
		desktopPane.add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type 2");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblType.setBounds(34, 90, 63, 23);
		desktopPane.add(lblType);
		
		//all the different typing available in pokemon

		String[] data = {"--Select--","Normal", "Fire", "Water", "Electric", "Grass",
                "Ice", "Fighting", "Poison", "Ground", "Flying",
                "Psychic", "Bug", "Rock", "Ghost", "Dragon",
                "Dark", "Steel", "Fairy"};
		String[] data2 = {"Normal", "Fire", "Water", "Electric", "Grass",
                "Ice", "Fighting", "Poison", "Ground", "Flying",
                "Psychic", "Bug", "Rock", "Ghost", "Dragon",
                "Dark", "Steel", "Fairy"};
		
        addToMap();
        
        
        
       
       
        
        
			
		scrollPane.setVisible(true);
	
		//basically all of the stats
		
		String[] com = {"...",">", "<", "="};
		
		JLabel hpVal = new JLabel("hp");
		hpVal.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hpVal.setBounds(223, 44, 28, 23);
		desktopPane.add(hpVal);
		
		JLabel defTime = new JLabel("def");
		defTime.setFont(new Font("Tahoma", Font.PLAIN, 17));
		defTime.setBounds(220, 97, 28, 23);
		desktopPane.add(defTime);
		
		JLabel hpVal_2 = new JLabel("atk");
		hpVal_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hpVal_2.setBounds(373, 44, 28, 23);
		desktopPane.add(hpVal_2);
		
		JLabel hpVal_3 = new JLabel("spA");
		hpVal_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hpVal_3.setBounds(373, 90, 28, 23);
		desktopPane.add(hpVal_3);
		
		JLabel hpVal_4 = new JLabel("spD");
		hpVal_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hpVal_4.setBounds(520, 44, 41, 23);
		desktopPane.add(hpVal_4);
		
		JLabel hpVal_5 = new JLabel("spe\r\n");
		hpVal_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		hpVal_5.setBounds(520, 97, 28, 23);
		desktopPane.add(hpVal_5);
		
		hpCom = new JComboBox(com);
		hpCom.setBounds(248, 47, 41, 22);
		desktopPane.add(hpCom);
		
		defCom = new JComboBox(com);
		defCom.setBounds(248, 100, 41, 22);
		desktopPane.add(defCom);
		
		spACom = new JComboBox(com);
		spACom.setBounds(408, 93, 41, 22);
		desktopPane.add(spACom);
		
		atkCom = new JComboBox(com);
		atkCom.setBounds(408, 47, 41, 22);
		desktopPane.add(atkCom);
		
		spDCom = new JComboBox(com);
		spDCom.setBounds(557, 47, 41, 22);
		desktopPane.add(spDCom);
		
		speCom = new JComboBox(com);
		speCom.setBounds(557, 100, 41, 22);
		desktopPane.add(speCom);
		
		hpField = new JTextField();
		hpField.setBounds(312, 39, 51, 38);
		desktopPane.add(hpField);
		hpField.setColumns(10);
		
		defField = new JTextField();
		defField.setColumns(3);
		defField.setBounds(312, 92, 51, 38);
		desktopPane.add(defField);
		
		atkField = new JTextField();
		atkField.setColumns(3);
		atkField.setBounds(459, 39, 51, 38);
		desktopPane.add(atkField);
		
		spAField = new JTextField();
		spAField.setColumns(3);
		spAField.setBounds(459, 85, 51, 38);
		desktopPane.add(spAField);
		
		spDField = new JTextField();
		spDField.setColumns(3);
		spDField.setBounds(608, 39, 51, 38);
		desktopPane.add(spDField);
		
		speField = new JTextField();
		speField.setColumns(3);
		speField.setBounds(608, 92, 51, 38);
		desktopPane.add(speField);
		
			JComboBox type1Box = new JComboBox(data);
			type1Box.setBounds(89, 44, 109, 29);
			desktopPane.add(type1Box);
			JComboBox type2Box = new JComboBox(data);
			type2Box.setBounds(89, 90, 109, 29);
			desktopPane.add(type2Box);
		
		
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String type1 = null, type2 = null;
			int hp = -1, atk = -1, def = -1, spA = -1, spD = -1, spe = -1;	
			
			//removes values from the scrollpane and revalidates the contentpanel
			contentPanel.removeAll();
			contentPanel.revalidate();
			contentPanel.repaint();
			
			//makes sure all of the lists are cleared and ready to be used again
			if(!poke.isEmpty()) {
				poke.clear();
				hpList.clear();
				atkList.clear();
				defList.clear();
				spAList.clear();
				spDList.clear();
				speList.clear();
			}
			
			//grabs the different needed values for the search query
			if(!((String)type1Box.getSelectedItem()).equals("--Select--")) type1 = (String) type1Box.getSelectedItem();
			
			if(!((String)type2Box.getSelectedItem()).equals("--Select--")) type2 = (String) type2Box.getSelectedItem();
			
			//for the stat blocks
		    if(!((String)hpCom.getSelectedItem()).equals("...") && (checkVal(hpField.getText()).equals("Integer") )) hp = Integer.parseInt(hpField.getText());
		    if(!((String)defCom.getSelectedItem()).equals("...") && (checkVal(defField.getText()).equals("Integer") )) def = Integer.parseInt(defField.getText());
		    if(!((String)atkCom.getSelectedItem()).equals("...") && (checkVal(atkField.getText()).equals("Integer") )) atk = Integer.parseInt(atkField.getText());
		    if(!((String)spACom.getSelectedItem()).equals("...") && (checkVal(spAField.getText()).equals("Integer") )) spA = Integer.parseInt(spAField.getText());
		    if(!((String)spDCom.getSelectedItem()).equals("...") && (checkVal(spDField.getText()).equals("Integer") )) spD = Integer.parseInt(spDField.getText());
		    if(!((String)speCom.getSelectedItem()).equals("...") && (checkVal(speField.getText()).equals("Integer") )) spe = Integer.parseInt(speField.getText());
	
			
			
			getPokemonFromType(type1, type2, hp, atk, def, spA, spD, spe);
			addButtons(contentPanel);

			
			
			
			}
		});
		btnNewButton.setBounds(100, 392, 91, 23);
		desktopPane.add(btnNewButton);
		
		JButton removePoke = new JButton("Remove Pokemon");
		removePoke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pokeNum == 0) return;
				else {
				
				contentPanel.removeAll();
				contentPanel.revalidate();
				contentPanel.repaint();
				
				removePokemonTime(contentPanel);
				
				}
				
			}
	});
	removePoke.setBounds(250, 392, 150, 23);
	desktopPane.add(removePoke);
	
	JButton analysis = new JButton("Analysis");
	analysis.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(pokeNum == 0) return;
			
			analysisTime();
			
			
			
		}
});
	
analysis.setBounds(450, 392, 91, 23);
desktopPane.add(analysis);
	
		
		
		
        frame.setVisible(true);

	}//end main

}
