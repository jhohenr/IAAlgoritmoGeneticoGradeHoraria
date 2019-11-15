package util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Aula;
import model.Curso;
import model.Disciplina;
import model.Indisponibilidade;
import model.Professor;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Dados {

	private static Document leitura() throws ParserConfigurationException, SAXException, IOException {

		File fXmlFile = new File(
				"C:\\Users\\carol\\OneDrive\\\\Documentos\\IAF\\IAAlgoritmoGeneticoGradeHoraria\\src\\main\\resources\\dados.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		return doc;
	}

	public static ArrayList<Curso> lerCursos() {

		ArrayList<Curso> items = new ArrayList<Curso>();

		try {
			Document doc = leitura();
			NodeList nList = doc.getElementsByTagName("class");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Curso item = new Curso(eElement.getAttribute("id"), eElement.getAttribute("name"));
					items.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Professor> lerProfessores() {

		ArrayList<Professor> items = new ArrayList<Professor>();

		try {
			Document doc = leitura();
			NodeList nList = doc.getElementsByTagName("teacher");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Professor item = new Professor(eElement.getAttribute("id"), eElement.getAttribute("name"));
					items.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Disciplina> lerDisciplinas() {

		ArrayList<Disciplina> items = new ArrayList<Disciplina>();

		try {
			Document doc = leitura();
			NodeList nList = doc.getElementsByTagName("subject");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Disciplina item = new Disciplina(eElement.getAttribute("id"), null, eElement.getAttribute("name"));
					items.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Aula> lerAulas(ArrayList<Disciplina> disciplinas) {

		ArrayList<Aula> items = new ArrayList<Aula>();

		try {
			Document doc = leitura();
			NodeList nList = doc.getElementsByTagName("lesson");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					Aula item = new Aula(eElement.getAttribute("id"), eElement.getAttribute("subjectid"),
							eElement.getAttribute("teacherid"));

					for (Disciplina disciplina : disciplinas) {
						if (disciplina.id.equals(eElement.getAttribute("subjectid"))) {
							disciplina.idCurso = eElement.getAttribute("classid");
						}
					}

					items.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

	public static ArrayList<Indisponibilidade> lerIndisponibilidades() {

		ArrayList<Indisponibilidade> items = new ArrayList<Indisponibilidade>();

		try {
			Document doc = leitura();
			NodeList nList = doc.getElementsByTagName("teacher");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String[] timeoffs = eElement.getAttribute("timeoff").split(",");
					int day = 0;
					for (String timeoff : timeoffs) {
						day++;
						int cont = 0;
						for (char horary : timeoff.toCharArray()) {
							if (horary == '0') {
								Indisponibilidade item = new Indisponibilidade(eElement.getAttribute("id"), day, cont);
								items.add(item);
							}
							cont++;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return items;
	}

}
