package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ModelPreference {



	public static void main(String[] args) throws IOException {
		parse();
		
		

		lire();

		String c = tableau_to_chain(listformule);
		System.out.println("==="+listformule.get(0));
		c = c.replaceAll(" ", "#");
		String split[] = chaine_to_tableau(c);
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < split.length - 1; i++) {
			if (split[i].equals(""))
				split[i] = split[i + 1];
		}
		for (int i = 0; i < split.length; i++) {
			liste.add(split[i]);

		}
		List<String> liste_final = removeDuplicatedItems(liste);
		
//		 Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
//			    public int compare(String str1, String str2) {
//			        int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
//			        if (res == 0) {
//			            res = str1.compareTo(str2);
//			        }
//			        return res;
//			    }
//			};
//
//			Collections.sort(liste_final, ALPHABETICAL_ORDER);
		//System.out.println("======="+liste_final);
		// System.out.println(liste_final.size());
		// System.out.println(listformule.size());
		

		int N = liste_final.size();
		
		///////// TABle de verité //////////////

		int rows = (int) Math.pow(2, N);
		String x = new String("");
		String tableVerite[] = new String[rows];
		System.out.print("|");
		for (int i = 0; i < liste_final.size(); i++) {
			System.out.print(liste_final.get(i)+"");
		}
		System.out.print("|");
		System.out.println();

		// Affichage de la table 
		for (int i = 0; i < rows; i++) {
			for (int j = N - 1; j >= 0; j--) {
				x = x + "" + (i / (int) Math.pow(2, j)) % 2;
			}
			tableVerite[i] = x;
			System.out.println("|"+tableVerite[i] + "|");
			x = "";
			 //System.out.println();

		}
//
		List<String> SansTypp = new ArrayList<>();
		List<String> AvecTypp = new ArrayList<>();
		for (int i = 0; i < listformule.size(); i++) {

			if (listformule.get(i).indexOf("typ") == -1) {
				// SansTyp[i]=listformule.get(i);
				SansTypp.add(listformule.get(i));
			} else {

				AvecTypp.add(listformule.get(i));
			}
		}

		////////////////////////////////// AfFICHER///////////////////////////////////////////////:
		for (int i = 0; i < SansTypp.size(); i++) {
			// System.out.println("*************" + SansTypp.get(i) + "taile" +
			// SansTypp.size());

		}
		/******************************************************/

		String valeur = " ";

		// if((liste_final.get(0)+" "+"imp"+"
		// "+liste_final.get(3)).equals(SansTypp.get(2)))
		// {
		// System.out.println(("zzzzzzzzzzzzzzzzzzzzzzzzzz"));
		// }

		for (int k = 0; k < liste_final.size(); k++) {
			for (int j = k + 1; j < liste_final.size(); j++) {

				if (k != j) {
					for (int m = 0; m < SansTypp.size(); m++) {
						// System.out.println();
						// System.out.println("nn"+SansTyp[13]);
						/// "a imp b",
						if ((liste_final.get(k) + " " + "imp" + " " + liste_final.get(j))
								.equals(SansTypp.get(m).toString())) {

							for (int i = 0; i < tableVerite.length; i++) {
								valeur = tableVerite[i];
								if (valeur.charAt(k) != valeur.charAt(j) && valeur.charAt(k) == '1') {
									tableVerite[i] = "nul";

								}
							}
						}
						// "c imp NOT d",

						if ((liste_final.get(k) + " " + "imp" + " " + "NOT" + " " + liste_final.get(j))
								.equals(SansTypp.get(m).toString())) {
							for (int i = 0; i < tableVerite.length; i++) {
								valeur = tableVerite[i];
								if (valeur.charAt(k) == valeur.charAt(j) && valeur.charAt(k) == '1') {
									tableVerite[i] = "nul";
								}
							}
						}

						// "NOT c imp d",

						if (("NOT" + " " + liste_final.get(k) + " " + "imp" + " " + liste_final.get(j))
								.equals(SansTypp.get(m).toString())) {
							for (int i = 0; i < tableVerite.length; i++) {
								valeur = tableVerite[i];
								if (valeur.charAt(k) == valeur.charAt(j) && valeur.charAt(k) == '0') {
									tableVerite[i] = "nul";
								}
							}
						}

					}
				}

			}
		}
		// static List<Noeuds> listTemp;
		// listTemp = new ArrayList<Noeuds>();

		//
		ArrayList<ArrayList<String>> listOLists = new ArrayList<ArrayList<String>>();
		ArrayList<String> singleList = new ArrayList<String>();
		// singleList.add("hello");
		// singleList.add("world");
		// listOLists.add(singleList);

		ArrayList<String> anotherList = new ArrayList<String>();
		anotherList.add("this is another list");
		// listOLists.add(anotherList);

		// System.out.println("iiiiiiiii
		// listoliste"+listOLists+"world======="+listOLists.get(0).get(1));
		/////////////////////////////////////////////////
		// création des rangs
		// for (int i = 0; i < rang.size(); i++) {
		// rang.get(i).add(e);
		//
		// }

		for (int i = 0; i < rows; i++) {

			if (tableVerite[i] != "nul") {
				singleList.add(tableVerite[i]);
				// rang1[z]=tableVerite[i];
				// System.out.println(rang1[z]+"");
			}

		}

		listOLists.add(singleList);
		//System.out.println("évolution des rang");
	
		System.out.println();

		//System.out.println("rang 0: "+listOLists);


		// singleList.
		// System.out.println(singleList);
		// System.out.println(AvecTypp+"avctyp");
		////////////////////////////// range 2
		ArrayList<String> singleList2 = new ArrayList<String>();

		for (int k = 0; k < liste_final.size(); k++) {
			for (int j = 0; j < liste_final.size(); j++) {

				if (k != j) {

					////
					for (int m = 0; m < AvecTypp.size(); m++) {
						// System.out.println();
						// System.out.println("nn"+SansTyp[13]);

						/// "typ a imp not b 10",

						if (("typ" + " " + liste_final.get(k) + " " + "imp" + " " + "not" + " " + liste_final.get(j))
								.equals(AvecTypp.get(m))) {
							for (int i = 0; i < listOLists.get(0).size(); i++) {
								// System.out.println("xxxx"+listOLists.get(0).size());
								if (singleList.get(i).charAt(k) == singleList.get(i).charAt(j)
										&& singleList.get(i).charAt(k) == '1') {
									// singleList.add(listOLists.get(0).get(i));
									singleList2.add(listOLists.get(0).get(i));
									// System.out.println(listOLists.get(0).get(i));
									listOLists.get(0).set(i, "---");
								}
							}
						}

						/// "typ a imp not b 10",

						if (("typ" + " " + liste_final.get(k) + " " + "imp" + " " + liste_final.get(j))
								.equals(AvecTypp.get(m))) {
							for (int i = 0; i < listOLists.get(0).size(); i++) {
								// System.out.println("xxxx"+listOLists.get(0).size());
								if (singleList.get(i).charAt(k) != singleList.get(i).charAt(j)
										&& singleList.get(i).charAt(k) == '1') {
									// singleList.add(listOLists.get(0).get(i));
									singleList2.add(listOLists.get(0).get(i));
							//		 System.out.println(listOLists.get(0).get(i));
									listOLists.get(0).set(i, "---");
								}
							}
						}

					}

				}

			}

		}
		// listOLists.add(singleList);
		// singleList.clear();
		// System.out.println(listOLists);

		listOLists.add(singleList2);
	//	System.out.println("rang 1: "+listOLists.get(0));
		//System.out.println("rang 0: "+listOLists.get(0));
		
		///////////////////////////
		/////// ****************************************************
		// *********************************************************
		/// *********************************************************

		// singleList.
		// System.out.println(singleList);
		// System.out.println(AvecTypp+"avctyp");
		////////////////////////////// range 2
			ArrayList<String> singleList3 = new ArrayList<String>();
		for (int k = 0; k < liste_final.size(); k++) {
			for (int j = 0; j < liste_final.size(); j++) {

				if (k != j) {

					////
					for (int m = 0; m < AvecTypp.size(); m++) {
						// System.out.println();
						// System.out.println("nn"+SansTyp[13]);

						/// "typ a imp not b 10",

						if (("typ" + " " + liste_final.get(k) + " " + "imp" + " " + liste_final.get(j))
								.equals(AvecTypp.get(m))) {
							for (int i = 0; i < singleList2.size(); i++) {
								// System.out.println("xxxx"+listOLists.get(0).size());
								if (singleList2.get(i).charAt(k) == singleList2.get(i).charAt(j)
										&& singleList2.get(i).charAt(k) == '1') {

									////////////
									for (int o = 0; o < singleList.size(); o++) {
										// System.out.println("xxxx"+listOLists.get(0).size());
										if (singleList.get(o).charAt(k) == singleList.get(o).charAt(j)
												&& singleList.get(o).charAt(k) == '1') {

											singleList3.add(listOLists.get(1).get(i));
											// System.out.println(listOLists.get(0).get(i));
											listOLists.get(1).set(i, "----");

										}
									}

								}
							}
						}
						////////// : typ a imp not b

						if (("typ" + " " + liste_final.get(k) + " " + "imp" + " " + "not" + " " + liste_final.get(j))
								.equals(AvecTypp.get(m))) {
							for (int i = 0; i < singleList2.size(); i++) {
								// System.out.println("xxxx"+listOLists.get(0).size());
								if (singleList2.get(i).charAt(k) != singleList2.get(i).charAt(j)
										&& singleList2.get(i).charAt(k) == '1') {

									////////////
									for (int o = 0; o < singleList.size(); o++) {
										// System.out.println("xxxx"+listOLists.get(0).size());
										if (singleList.get(o).charAt(k) != singleList.get(o).charAt(j)
												&& singleList.get(o).charAt(k) == '1') {

											singleList3.add(listOLists.get(1).get(i));
											// System.out.println(listOLists.get(0).get(i));
											listOLists.get(1).set(i, "----");

										}
									}

								}
							}
						}

					}

				}

			}

		}
		System.out.println("Le modéle de préférence: "+liste_final);

		listOLists.add(singleList3);
		for (int i = listOLists.size()-1; i >=0; i--) {
			System.out.println("rang "+i+": "+listOLists.get(i));

		}
		
		
		Scanner sc = new Scanner(System.in);
		//System.out.println("rez formule:");
		
		String s = "";
		while (s.compareTo("non") != 0) {

		

		parse2();

		lire2();

		String c1 = tableau_to_chain(listformule2);

		c1 = c1.replaceAll(" ", "#");
		String split1[] = chaine_to_tableau(c1);

		List<String> liste_question = new ArrayList<String>();

		for (int i = 0; i < split1.length; i++) {
			liste_question.add(split1[i]);

		}

		for (int i = 0; i < liste_question.size(); i++) {
			for (int j = 0; j < liste_question.size(); j++) {
				if(i!=j){
					if (liste_question.get(i).equals(liste_question.get(j))) {
						liste_question.set(i,"#");
					

				}}
			}
		}
		
		//System.out.println("variable" + liste_question);
		//System.out.println(comparerDeuuxListe(liste_final, liste_question));
		
		

		
		
////////////////////*******************************************/////////////////		
////////////////////*******************************************/////////////////
////////////////////*******************************************/////////////////
////////////////////*******************************************/////////////////

////////////////////*******************************************/////////////////		
////////////////////*******************************************/////////////////
////////////////////*******************************************/////////////////
////////////////////*******************************************/////////////////

List<Integer> resu=new ArrayList<Integer>(); ;
resu=comparerDeuuxListe(liste_final,liste_question);


int tab[]={0,1}; 
int tab2[]={0,0,0}; //

String equation= listformule2.get(0);
// typ a imp not b
int pos = equation.indexOf("imp");
tab2[2]=0; 
//System.out.println("valeur "+pos);
String val = equation.substring(0,pos-1);// typ a\s
String val1 = equation.substring(pos+4,equation.length());//not b
//System.out.println("valeur "+val1);


//

if(val.indexOf("and")>0){tab2[2]=1;}

tab2[0]=4;
if(val.indexOf("ot typ")>0)
{  tab2[0]=3;   }
else if(val.indexOf("yp")>0)
{ tab2[0]=1;    }
else if(val.indexOf("ot")>0)
{   tab2[0]=2;  }

//
tab2[1]=4;
if(val1.indexOf("ot typ")>0)
{  tab2[1]=3;   }
else if(val1.indexOf("yp")>0)
{  tab2[1]=1;   }
else if(val1.indexOf("ot")>0)
{  tab2[1]=2;   }


int w=1;			
//System.out.println("tab2[0]"+tab2[0]);
//System.out.println("tab2[1]"+tab2[1]);
//System.out.println("tab2[2]"+tab2[2]);
boolean arreter = false;
///  chercher dans les rangs   listOLists
//comparerDeuuxListe(liste_final,liste_question).get(0)
/////  typ a imp b or imp not b
if(tab2[0]==1 ){//debut typ a
for (int i=0;i<listOLists.size()-1;i++)
{
for (int j=0;j<listOLists.get(i).size();j++)
{ // on cherche le 1er 1 pour typ a car c'est le plus typique
if (listOLists.get(i).get(j).charAt(resu.get(0))=='1' )// charat a resu.get(0) ret valeur a
{
if(tab2[2]==1 && listOLists.get(i).get(j).charAt(resu.get(1))=='1'){
w=2;
	
}


if(tab2[1]==4 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){
System.out.println(listformule2.get(0)+"==>oui !");
arreter = true;
break;

}else if(tab2[1]==4 && listOLists.get(i).get(j).charAt(resu.get(w))=='0' )
{
System.out.println(listformule2.get(0)+"==> non !");
arreter = true;
break;

}

if(tab2[1]==2 && listOLists.get(i).get(j).charAt(resu.get(w))=='0' ){
System.out.println(listformule2.get(0)+"==>oui !");
arreter = true;
break;

}else if(tab2[1]==2 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' )
{
System.out.println(listformule2.get(0)+"==>non !");
arreter = true;
break;

}
//  a imp 3 not typ
if(tab2[1]==3 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){

for (int k=0;k<i;k++) // rja3 n9alab f lil ta7te
{
for (int m=0;m<listOLists.get(k).size();m++)
{	
	if(listOLists.get(k).get(m).charAt(resu.get(w))=='1' )
	{

		System.out.println(listformule2.get(0)+"==>oui !");
		arreter = true;
        break;
		
	
	
}
}
}

}
/////
///
if(tab2[1]==1 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){

		for (int k=0;k<i;k++)
		{
		for (int m=0;m<listOLists.get(k).size();m++)
		{	
			if(listOLists.get(k).get(m).charAt(resu.get(w))=='1' )
			{

				System.out.println(listformule2.get(0)+"==>non !");
				arreter = true;
	            break;
				
			
			
		}
		}
		if(arreter==true){
			break;
		}
		}
		
		if(arreter==false){
			System.out.println(listformule2.get(0)+"==>oui !");
		}
	}
///







}	


}
if(arreter)
break;
}

}// end if
///  not typ imp a or imp not a
arreter = false;
if(tab2[0]==3){
for (int i=listOLists.size()-1;i>0;i--)
{
for (int j=0;j<listOLists.get(i).size();j++)
{
if (listOLists.get(i).get(j).charAt(resu.get(0))=='1')
{
if(tab2[2]==1 && listOLists.get(i).get(j).charAt(resu.get(1))=='1'){
w=2;
	
}
if(tab2[1]==4 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){
System.out.println(listformule2.get(0)+"==>oui !");
arreter = true;
break;

}else if(tab2[1]==4 && listOLists.get(i).get(j).charAt(resu.get(w))=='0' )
{
System.out.println(listformule2.get(0)+"==>non !");
arreter = true;
break;

}

if(tab2[1]==2 && listOLists.get(i).get(j).charAt(resu.get(w))=='0' ){
System.out.println(listformule2.get(0)+"==>oui !");
arreter = true;
break;

}else if(tab2[1]==2 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' )
{
System.out.println(listformule2.get(0)+"==>non !");
arreter = true;
break;

}


/////
///
if(tab2[1]==1 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){

		for (int k=0;k<i;k++)
		{
		for (int m=0;m<listOLists.get(k).size();m++)
		{	
			if(listOLists.get(k).get(m).charAt(resu.get(w))=='1' )
			{

				System.out.println(listformule2.get(0)+"==>non !");
				arreter = true;
	            break;
				
			
			
		}
		}
		if(arreter==true){
			break;
		}
		}
		
		if(arreter==false){
			System.out.println(listformule2.get(0)+"==>oui !");
		}
	}

if(tab2[1]==3 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){
	
			for (int k=0;k<i;k++)
			{
			for (int m=0;m<listOLists.get(k).size();m++)
			{	
				if(listOLists.get(k).get(m).charAt(resu.get(w))=='1' )
				{

					System.out.println(listformule2.get(0)+"==>oui !");
					arreter = true;
		            break;
					
				
				
			}
			}
			if(arreter==true){
				break;
			}
			}
			
			if(arreter==false){
				System.out.println(listformule2.get(0)+"==>non !");
			}
		}
///

}	


}
if(arreter)
break;
}

}// end if




/////////////////////

///  a imp b or imp not a
arreter = false;
if(tab2[0]==4){
for (int i=0;i<listOLists.size()-1;i++)
{
for (int j=0;j<listOLists.get(i).size();j++)
{
if (listOLists.get(i).get(j).charAt(resu.get(0))=='1')
{

if(tab2[2]==1 && listOLists.get(i).get(j).charAt(resu.get(1))=='1'){
w=2;
	
}

if(tab2[1]==4 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){
System.out.println(listformule2.get(0)+"==>oui !");
arreter = true;
break;

}else if(tab2[1]==4 && listOLists.get(i).get(j).charAt(resu.get(w))=='0' )
{
System.out.println(listformule2.get(0)+"==>non !");
arreter = true;
break;

}

if(tab2[1]==2 && listOLists.get(i).get(j).charAt(resu.get(w))=='0' ){
System.out.println(listformule2.get(0)+"==>oui !");
arreter = true;
break;

}else if(tab2[1]==2 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' )
{
System.out.println(listformule2.get(0)+"==>non !");
arreter = true;
break;

}

if(tab2[1]==1 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){

for (int k=0;k<i;k++)
{
for (int m=0;m<listOLists.get(k).size();m++)
{	
	if(listOLists.get(k).get(m).charAt(resu.get(w))=='1' )
	{

		System.out.println(listformule2.get(0)+"==>non !");
		arreter = true;
        break;
		
	
	
}
}
if(arreter==true){
	break;
}
}

if(arreter==false){
	System.out.println(listformule2.get(0)+"==>oui !");
}
}


///
if(tab2[1]==3 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){

	for (int k=0;k<i;k++)
	{
	for (int m=0;m<listOLists.get(k).size();m++)
	{	
		if(listOLists.get(k).get(m).charAt(resu.get(w))=='1' )
		{

			System.out.println(listformule2.get(0)+"==>oui !");
			arreter = true;
         break;
			
		
		
	}
	}
	if(arreter==true){
		break;
	}
	}
	
	if(arreter==false){
		System.out.println(listformule2.get(0)+"==>non !");
	}
}
///

}	


}
if(arreter)
break;
}

}// end if


/////  //////////
arreter = false;
boolean vall1 = false;

if(tab2[0]==2){
for (int i=0;i<listOLists.size()-1;i++)
{
for (int j=0;j<listOLists.get(i).size();j++)
{

if (listOLists.get(i).get(j).charAt(resu.get(0))=='0')
{
if(tab2[2]==1 && listOLists.get(i).get(j).charAt(resu.get(1))=='1'){
w=2;
	
}

if(tab2[1]==4 && listOLists.get(i).get(j).charAt(resu.get(w))=='0' ){
vall1=true;
}
if(tab2[1]==2 && listOLists.get(i).get(j).charAt(resu.get(w))=='1' ){
vall1=true;
}

/////
//not a imp 3 not typ
}

}

// System.out.println(" "+singleList)
}
if(vall1){System.out.println(listformule2.get(0)+"==>non!");}
if(vall1==false){System.out.println(listformule2.get(0)+"==>oui!");}

}


		System.out.println("voulez vous poser une autre questions ?");
		s = sc.nextLine();
		
		}
		
	}
	public static void saut_ligne(String str) {
		if (str.contains(";"))
			str.replaceAll(";", "\n");

	}

	// (\(\S+ and \S+\)\s*)?(?:[a-z] )?imp \.?[a-z]
	public static void parse() throws IOException {

		System.out.println("===========================================");
		FileWriter fs = new FileWriter("./src/in.txt");
		BufferedWriter out = new BufferedWriter(fs);

		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir les Formules:");
		String s = "";
		while (s.compareTo("ok") != 0) {
			s = sc.nextLine();
			// [a-z[.]]?[a-z]
			// [\\(*[a-z[.]]?[a-z] and? [[a-z[.]]?[a-z]]?\\)*]+ (imp||equi)
			// [\\(*[a-z[.]]?[a-z] and? [[a-z[.]]?[a-z]]?\\)*]+
			// if (s.matches("[a-z[.]]?[a-z[\\(]]*(\\s(and||or)
			// [a-z[.]]?[a-z[\\)][\\(]]*)* (imp||equi)
			// [a-z[.]]?[a-z[\\(]]*(\\s(and||or) [a-z[.]]?[a-z[\\)][\\(]]*)*"))
			// {
			if (s.matches("[typ[not]]*\\s?[a-z] imp [typ[not]]*\\s?[a-z]")) {

				// System.out.println(s);
				//System.out.println("taper ok pour terminer");

				out.write(s + "\r\n");
			} else if (s.equals("ok")) {
				System.out.println("======================");
			} else {
				System.out.println("error syntax");
			}
		}

		out.close();

	}

	
	
	public static void parse2() throws IOException {

		System.out.println("===========================================");
		FileWriter fs = new FileWriter("./src/question.txt");
		BufferedWriter out = new BufferedWriter(fs);

		Scanner sc = new Scanner(System.in);
		System.out.println("posez une question?");
		String s = "";
	//	while (s.compareTo("ok") != 0) {
				s = sc.nextLine();
				//[a-z[.]]?[a-z]
				//[\\(*[a-z[.]]?[a-z] and? [[a-z[.]]?[a-z]]?\\)*]+ (imp||equi) [\\(*[a-z[.]]?[a-z] and? [[a-z[.]]?[a-z]]?\\)*]+
				//if (s.matches("[a-z[.]]?[a-z[\\(]]*(\\s(and||or) [a-z[.]]?[a-z[\\)][\\(]]*)* (imp||equi) [a-z[.]]?[a-z[\\(]]*(\\s(and||or) [a-z[.]]?[a-z[\\)][\\(]]*)*")) {

				//System.out.println(s);
				//System.out.println("taper ok pour terminer");
//s.matches("[typ[not]]*\\s?[a-z] imp [typ[not]]*\\s?[a-z]")|| s.matches("[not[typ]]*\\s?[a-z] imp [not[typ]]*\\s?[a-z]")) {
				
				if (true){
					out.write(s + "\r\n");
					
				}
			
//				
				else {
					System.out.println("error syntax");
					//s = sc.nextLine();

				}
			
		
		
		out.close();

	}


	static List<String> listformule2;

	public static void lire2() {
		try {

			InputStream flux = new FileInputStream("./src/question.txt");
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			int cpt = 0;
			listformule2 = new ArrayList<String>();

			while ((ligne = buff.readLine()) != null) {
				listformule2.add(ligne);
				// System.out.println(listformule);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static String[] chaine_to_tableau(String c) {

		String split[] = c.split("#");
		for (int i = 0; i < split.length - 1; i++) {
			for (int j = 1; j < split.length; j++) {
				if (split[i].equals("imp") || split[i].equals("typ") || split[i].equals("not"))
					split[i] = split[j];
				split[i].trim();
			}

		}

		return split;
	}

	public static String tableau_to_chain(List<String> tableauChaine) {
		String c = "";
		for (int i = 0; i < tableauChaine.size(); i++) {
			c = c + tableauChaine.get(i) + "#";
		}
		return c;
	}

	public static List<String> removeDuplicatedItems(List<String> liste) {
		Set<String> set = new HashSet<String>();
		set.addAll(liste);
		return new ArrayList<String>(set);
	}

	public static List<Integer> comparerDeuuxListe(List<String> listfinal, List<String> listquestion) {
		List<Integer> resu = new ArrayList<Integer>();

		int cpt = 0;
		for (int i = 0; i < listquestion.size(); i++) {
			for (int j = 0; j < listfinal.size(); j++) {
				if (listquestion.get(i).equals(listfinal.get(j))) {
					resu.add(j);
				}
			}
			// return "ok";

		}
		return resu;
	}

	public static List<String> chaine_to_tableau1(String c) {
		List<String> resu = new ArrayList<String>();
		;

		String split[] = c.split("#");
		for (int i = 0; i < split.length - 1; i++) {
			if (!split[i].equals("imp") || !split[i].equals("typ") || !split[i].equals("not"))
				// split[i] = split[j];
				resu.add(split[i]);
			split[i].trim();

		}

		return resu;
	}

	static List<String> listformule;

	public static void lire() {
		try {

			InputStream flux = new FileInputStream("./src/in.txt");
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			int cpt = 0;
			listformule = new ArrayList<String>();

			while ((ligne = buff.readLine()) != null) {
				listformule.add(ligne);
				// System.out.println(listformule);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	// public static void display(ArrayList<ArrayList<String>> listOLists) {
	// for (int i = 0; i < listOLists.size(); i++) {
	// System.out.println(listOLists.get(i).get(0));
	//
	// }
	//
	// }

	////////////////// MAIN......................./////////////////////////
	// System.out.println(" "+singleList);

}

/*
 * 
 * 
*/
