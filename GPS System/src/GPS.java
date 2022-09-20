import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



@SuppressWarnings("serial")
public class GPS extends JFrame {
private int count = 0;
private int size = 0;
private Vertex start;
private Vertex end;
private Graph g = new Graph("MapInformation.txt");
private JTextArea path;
ArrayList<Vertex> temp = g.allLocations();

	public GPS() {
		super("GPS");
		setSize(555,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//total = 0;
		JPanel buttonPanel = new JPanel();
			Graph.returnAddress = true;
			Graph.useDistCost = false;
			Graph g = new Graph("MapInformation.txt");	
		
			ArrayList<String> ret = g.allPlaces();
			
			
		JButton item1 = new JButton ((ret).get(0));
		   item1.addActionListener(new item1Listener());
		JButton item2 = new JButton ((ret).get(1));
		   item2.addActionListener(new item2Listener());
		JButton item3 = new JButton ((ret).get(2));
		   item3.addActionListener(new item3Listener());
		JButton item4 = new JButton (ret.get(3));
		   item4.addActionListener(new item4Listener());
		JButton item5 = new JButton (ret.get(4));
		   item5.addActionListener(new item5Listener());
		JButton item6 = new JButton (ret.get(5));
		   item6.addActionListener(new item6Listener());
		JButton item7 = new JButton (ret.get(6));
		   item7.addActionListener(new item7Listener());
		JButton item8 = new JButton (ret.get(7));
		   item8.addActionListener(new item8Listener());
		JButton item9 = new JButton (ret.get(8));
		   item9.addActionListener(new item9Listener());
		JButton item10 = new JButton (ret.get(9));
		   item10.addActionListener(new item10Listener());
		JButton item11 = new JButton (ret.get(10));
		   item11.addActionListener(new item11Listener());
		JButton item12 = new JButton (ret.get(11));
		   item12.addActionListener(new item12Listener());
		JButton item13 = new JButton (ret.get(12));
		   item13.addActionListener(new item13Listener());
		JButton item14 = new JButton(ret.get(13));
			item14.addActionListener(new item14Listener());
		JButton item15 = new JButton(ret.get(14));
			item15.addActionListener(new item15Listener());
		JButton item16 = new JButton(ret.get(15));
			item16.addActionListener(new item16Listener());
		JButton item17 = new JButton(ret.get(16));
			item17.addActionListener(new item17Listener());
		JButton item18 = new JButton(ret.get(17));
			item18.addActionListener(new item18Listener());
		JButton item19 = new JButton(ret.get(18));
			item19.addActionListener(new item19Listener());
		JButton item20 = new JButton(ret.get(19));
			item20.addActionListener(new item20Listener());
			
		 path = new JTextArea(10,40);
			path.setEditable(false);
			
		   buttonPanel.add(item1);
		   buttonPanel.add(item2);
		   buttonPanel.add(item3);
		   buttonPanel.add(item4);
		   buttonPanel.add(item5);
		   buttonPanel.add(item6);
		   buttonPanel.add(item7);
		   buttonPanel.add(item8);
		   buttonPanel.add(item9);
		   buttonPanel.add(item10);
		   buttonPanel.add(item11);
		   buttonPanel.add(item12);
		   buttonPanel.add(item13);
		   buttonPanel.add(item14);
		   buttonPanel.add(item15);
		   buttonPanel.add(item16);
		   buttonPanel.add(item17);
		   buttonPanel.add(item18);
		   buttonPanel.add(item19);
		   buttonPanel.add(item20);
		   buttonPanel.add(path);
		   
		   getContentPane().add(buttonPanel);
	}
	
	private class item1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(0);
        		 
        	}
        	else if(count == 1) {
        		end = temp.get(0); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        	}  
	}
	private class item2Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(1);
        	}
        	else if(count == 1) {
        		end = temp.get(1); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;   
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }
	}  
	private class item3Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(count == 0) {
        		count++;
        		start = temp.get(2);
        	}
        	else if(count == 1) {
        		end = temp.get(2); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;        
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
            
        }  
	}
	private class item4Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(3);
        	}
        	else if(count == 1) {
        		end = temp.get(3); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
    		}
        	}  
	}
	
	private class item5Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(4);
        	}
        	else if(count == 1) {
        		end = temp.get(4); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	
	private class item6Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(5);
        	}
        	else if(count == 1) {
        		end = temp.get(5); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item7Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(6);
        	}
        	else if(count == 1) {
        		end = temp.get(6); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item8Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(7);
        	}
        	else if(count == 1) {
        		end = temp.get(7); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item9Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(8);
        	}
        	else if(count == 1) {
        		end = temp.get(8); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item10Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(9);
        	}
        	else if(count == 1) {
        		end = temp.get(9); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item11Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(10);
        	}
        	else if(count == 1) {
        		end = temp.get(10); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item12Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(11);
        	}
        	else if(count == 1) {
        		end = temp.get(11); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	
	private class item13Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(12);
        	}
        	else if(count == 1) {
        		end = temp.get(12); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item14Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(13);
        	}
        	else if(count == 1) {
        		end = temp.get(13); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item15Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(14);
        	}
        	else if(count == 1) {
        		end = temp.get(14); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	
	private class item16Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(15);
        	}
        	else if(count == 1) {
        		end = temp.get(15); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item17Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(16);
        	}
        	else if(count == 1) {
        		end = temp.get(16); 
        		
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item18Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(17);
        	}
        	else if(count == 1) {
        		end = temp.get(17); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	
	private class item19Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(18);
        	}
        	else if(count == 1) {
        		end = temp.get(18); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	private class item20Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
          
        	if(count == 0) {
        		count++;
        		start = temp.get(19);
        	}
        	else if(count == 1) {
        		end = temp.get(19); 
        		String test = shortestPath(start, end).toString(start.symbol);
        		path.append(test);
        	path.append("\n==========================\n");
        	count = 0;
        	size++;
    		if(size == 4) {
    			path.setText(test); 
    			path.append("\n==========================\n");
    			size = 0;
    		}
        	}
        }  
	}
	
public Path shortestPath(Vertex begin, Vertex ending) {
	UnsortedArrayPriorityQueue<Path> ret = new UnsortedArrayPriorityQueue<>();
		
		ret.add(new Path(begin.symbol, 0, begin.symbol));
		
			ArrayList<String> visit = new ArrayList<>();
			int nextCost;
			String currPath;
			String nextPath;
			String curr;
			
			while (!ret.isEmpty()) {
				Path nextEntry = ret.remove();
				if(visit.contains(nextEntry.vertex)) continue;
				visit.add(nextEntry.vertex);
				if((nextEntry.vertex).equals(ending.symbol)) {
					return nextEntry;
				}
				else {
				 
				int cost = nextEntry.cost;
				 currPath = nextEntry.path;
				ArrayList<Edge> temp = g.getPredecessors(nextEntry.vertex);
					for(Edge V : temp) {
						curr = V.toVertex.symbol;
						nextCost = cost + V.timeCost;
						nextPath = currPath + V.toVertex.symbol;
						ret.add(new Path(curr, nextCost, nextPath));
					}
				}
					
			}
			
			
			return new Path("No Path", 0, "No Path");
		
	}


}
