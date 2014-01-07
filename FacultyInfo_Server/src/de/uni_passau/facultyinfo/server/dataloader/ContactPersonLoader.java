package de.uni_passau.facultyinfo.server.dataloader;

import java.util.UUID;

import de.uni_passau.facultyinfo.server.dao.ContactPersonDAO;
import de.uni_passau.facultyinfo.server.dto.ContactGroup;
import de.uni_passau.facultyinfo.server.dto.ContactPerson;

public class ContactPersonLoader {
	public String load() {
		String result = "";
		ContactPersonDAO contactPersonDAO = new ContactPersonDAO();
		result += "Deleting from Table contactpersons";
		contactPersonDAO.deleteAllContactPersons();
		result += " -- done\n";
		result += "Deleting from Table contactgroups";
		contactPersonDAO.deleteAllContactGroups();
		result += " -- done\n";

		result += "Loading contact persons...\n";

		ContactGroup cg1 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Juniorprofessur für Wirtschaftsinformatik mit Schwerpunkt E-Commerce",
				null);
		contactPersonDAO.createContactGroup(cg1);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Michael Scholz", "ITZ, Raum 255",
				"0851 509-2416", "michael.scholz@uni-passau.de",
				"Juniorprofessor", cg1));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Claudia Reitmayer", "ITZ, Raum 261",
				"0851 509-2591", "claudia.reitmayer@uni-passau.de",
				"Sekretärin", cg1));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Olga Ivanova", "ITZ, Raum 254",
				"0851 509-2415", "olga.ivanova@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg1));

		ContactGroup cg2 = new ContactGroup(UUID.randomUUID().toString(),
				"Lehreinheit für Statistik", null);
		contactPersonDAO.createContactGroup(cg2);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Gertrud Moosmüller",
				"HK 14B, Raum 222", "[49] (851) 509-2410",
				"moosmueller@uni-passau.de", "Professorin", cg2));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl. Kffr. Katrin Feigl",
				"HK 14 B, Raum 220", "0851 509 2588",
				"katrin.feigl@uni-passau.de",
				"Lehrkraft für besondere Aufgaben", cg2));

		ContactGroup cg3 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Accounting und Auditing",
				null);
		contactPersonDAO.createContactGroup(cg3);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Manuela Möller",
				"WiWi, Raum 010", "0851 509-2470",
				"Manuela.Moeller@uni-passau.de", "Lehrstuhlinhaberin", cg3));
		contactPersonDAO
				.createContactPerson(new ContactPerson(UUID.randomUUID()
						.toString(), "Gisela Rybarczik", "WiWi, Raum 011",
						"0851 509-2471", "rybarczik@uni-passau.de",
						"Sekretariat", cg3));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Lisa Frey", "WiWi, Raum 013",
				"0851 509-2474", "Lisa.Frey@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg3));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Christian Laschewski",
				"WiWi, Raum 012", "0851 509-2473",
				"Christian.Laschewski@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg3));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Manuel Risse", "WiWi, Raum 012",
				"0851 509-2473", "Manuel.Risse@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg3));

		ContactGroup cg4 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Accounting und Controlling",
				null);
		contactPersonDAO.createContactGroup(cg4);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Robert Obermaier",
				"WiWi, Raum 210", "+49 (0) 851 / 509 - 3270",
				"controlling@uni-passau.de", "Lehrstuhlinhaber", cg4));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Ulrike Haberl", "WiWi, Raum 211",
				"+49 (0) 851 / 509 - 3271", "controlling@uni-passau.de",
				"Sekretariat", cg4));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Kfm. Markus Dirmhirn",
				"WiWi, Raum 213", "+49 (0) 851 / 509 - 3275",
				"markus.dirmhirn@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg4));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "M.Sc. Florian Kaiser",
				"WiWi, Raum 213", "+49 (0) 851 / 509 - 3275",
				"florian.kaiser@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg4));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Kfm.Christian Meier",
				"WiWi, Raum 212", "+49 (0) 851 / 509 - 3273",
				"christian.meier@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg4));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Josef Schosser",
				"WiWi, Raum 214", "+49 (0) 851 / 509 - 3274",
				"josef.schosser@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg4));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Hdl. Felix Weißmüller",
				"WiWi, Raum 212", "+49 (0) 851 / 509 - 3273",
				"felix.weissmueller@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg4));

		ContactGroup cg5 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Finance und Banking",
				null);
		contactPersonDAO.createContactGroup(cg5);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Oliver Entrop",
				"WiWi, Room 006", "+49 (851) 509 2460",
				"oliver.entrop@uni-passau.de", "Chair Holder", cg5));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Sylvia Böhm", "WiWi, Room 007",
				"+49 (851) 509 2461", "sylvia.boehm@uni-passau.de",
				"Secretary", cg5));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Vw. Stefanie Baller",
				"WiWi, Room 008", "+49 (851) 509 2463",
				"stefanie.baller@uni-passau.de", "Research Assistant", cg5));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "M.Sc. Paul Dechant",
				"WiWi, Room 009", "+49 (851) 509 2464",
				"paul.dechant@uni-passau.de", "Research Assistant", cg5));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Kfm. Mathias Eickholt",
				"WiWi, Room 008", "+49 (851) 509 2463",
				"mathias.eickholt@uni-passau.de", "Research Assistant", cg5));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "M.Sc. Georg Fischer",
				"WiWi, Room 009", "+49 (851) 509 2464",
				"georg.fischer@uni-passau.de", "Research Assistant", cg5));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "M.Sc. Matthias Merkel",
				"WiWi, Room 120", "+49 (851) 509 2465",
				"matthias.merkel@uni-passau.de", "Research Assistant", cg5));

		ContactGroup cg6 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Finanzcontrolling",
				null);
		contactPersonDAO.createContactGroup(cg6);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Niklas Wagner",
				"103 (Wiwi)", "0851/509-3240", "fincon@uni-passau.de",
				"Lehrstuhlinhaber", cg6));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Jochen Wilhelm",
				"106 (Wiwi)", "0851/509-2408", "jochen.wilhelm@uni-passau.de",
				"Emeritus", cg6));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Andrea Huber", "102 (Wiwi)",
				"0851/509-3241", "fincon@uni-passau.de", "Sekretariat", cg6));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Thomas Wenger", "101 (Wiwi)",
				"0851/509-3243", "thomas.wenger@uni-passau.de",
				"Akademischer Rat", cg6));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Axel Buchner", "105 (Wiwi)",
				"0851/509-3245", "axel.buchner@uni-passau.de",
				"Akademischer Rat", cg6));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Kfm. Christoph Riedel", null,
				"0851/509-3241", "fincon@uni-passau.de", "Lehrbeauftragter",
				cg6));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Holger Blisse", null,
				"0851/509-3241", "Hblisse@yahoo.de", "Lehrbeauftragter", cg6));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Harald Kinateder", "104 (Wiwi)",
				"0851/509-3244", "harald.kinateder@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg6));

		ContactGroup cg7 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Finanzierung",
				null);
		contactPersonDAO.createContactGroup(cg7);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Jochen Wilhelm",
				"WiWi, Raum 112", "+49 (851) 509-2510",
				"jochen.wilhelm@uni-passau.de", "Lehrstuhlinhaber", cg7));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Christiana Augsburg",
				"WiWi, Raum 111", "+49 (851) 509-2511",
				"christiana.augsburg@uni-passau.de", "Sekretariat", cg7));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Peter von Snitkin",
				"WiWi, Raum 114", "+49 (851) 509-2514",
				"peter.snitkin@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg7));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Hans-Georg Schwarz",
				"WiWi, Raum 113", "+49 (851) 509-2513",
				"hans-georg.schwarz@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg7));

		ContactGroup cg8 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Internationales Management",
				null);
		contactPersonDAO.createContactGroup(cg8);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Carola Jungwirth",
				"WiWi, Raum 315", "0851-509-3250",
				"carola.jungwirth@uni-passau.de", "Lehrstuhlinhaberin", cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(),
				"Loren Barth, Dipl.-Kulturwirt. Univ.", "WiWi, Raum 311",
				"0851-509-3257", "loren.barth@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Stafanie Fuchs, M.Sc.", null,
				"0941-630916-14", "stefanie.fuchs@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Annika Ludwig, M.Sc.",
				"WiWi, Raum 310", "0851-509-3258",
				"annika.ludwig@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Elisabeth Müller",
				"WiWi, Raum 313", "0851-509-3254",
				"elisabeth.mueller@uni-passau.de", "Akademische Rätin a.Z.",
				cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Robert Pfeffer, M.Sc.",
				"WiWi, Raum 312", "0851-509-3255",
				"robert.pfeffer@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(),
				"Robert R. Richter, Dipl.-Kfm. Univ.", "WiWi, Raum 311",
				"0851-509-3255", "robert.richter@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Nobina Roy, Dipl.-Kffr. Univ.",
				"WiWi, Raum 312", "0851-509-3256", "nobina.roy@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg8));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(),
				"Susanne Ruckdäschel, Dipl.-Kulturwirt. Univ.",
				"WiWi, Raum 314", "0851-509-3253",
				"susanne.ruckdaeschel@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg8));

		ContactGroup cg9 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Management, Personal und Information",
				null);
		contactPersonDAO.createContactGroup(cg9);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Marina Fiedler",
				"WiWi, Raum 203", "0851 - 509 2491",
				"Marina.Fiedler@Uni-Passau.De", "Lehrstuhlinhaberin", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Monika Lehmann", "WiWi, Raum 202",
				"0851 - 509 2491", "Sekretariat.Fiedler@Uni-Passau.De",
				"Sekretariat", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Marcel Allscher", "WiWi, Raum 204",
				"0851 - 509 2494", "Marcel.Allscher@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter und Doktorand", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Caroline Baethge", "WiWi, Raum 308",
				"0851 - 509 2496", "Caroline.Baethge@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin und Doktorandin", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Miriam Flickinger",
				"WiWi, Raum 119", "0851 - 509 2495",
				"Miriam.Flickinger@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin und Post Doc", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Tina Gruber-Mücke",
				"WiWi, Raum 119", null, "gruber40@gw.uni-passau.de",
				"Post Doc", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Stephen Jeffrey",
				"WiWi, Raum 201", "0851 - 509 2493",
				"Stephen.Jeffrey@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter und Post Doc", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Fabian Schwister", "WiWi, Raum 204",
				null, "schwis01@gw.uni-passau.de", "Doktorand", cg9));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Heike Wißmann", "WiWi, Raum 204",
				"0851 - 509 2494", "Heike.Wissmann@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin und Doktorandin", cg9));

		ContactGroup cg10 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Marketing und Innovation",
				null);
		contactPersonDAO.createContactGroup(cg10);
		contactPersonDAO
				.createContactPerson(new ContactPerson(UUID.randomUUID()
						.toString(), "Prof. Dr. Jan H. Schumann",
						"WiWi, Raum 003", "+49 851 509-2421",
						"Sekretariat.Schumann@Uni-Passau.de",
						"Lehrstuhlinhaber", cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Rosemarie Neumeier",
				"WiWi, Raum 002", "+49 851 509-2421",
				"rosemarie.neumeier@uni-passau.de", "Sekretariat", cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Eva Anderl, M.A.", "WiWi, Raum 005",
				"+49 851 509-2421", "eva.anderl@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Nicole Heß, M.Sc.",
				"WiWi, Raum 004", "+49 851 509-2427",
				"nicole.hess@uni-passau.de", "Wissenschaftliche Mitarbeiterin",
				cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Björn Hüttel, M.Sc.",
				"WiWi, Raum 005", "+49 851 509-2428",
				"bjoern.huettel@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Armin März, M.A.", "WiWi, Raum 003",
				"+49 851 509-2428", "armin.maerz@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Sebastian Schubach, M.A. M.Sc.",
				"WiWi, Raum 005", "+49 851 509-2428",
				"Sebastian.Schubach@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Christian Wagner, Dipl.-Kfm.",
				"WiWi, Raum 004", "+49 851 509-2426",
				"christian.wagner@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg10));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dimitri Golovko, Dipl.-Kfm.", null,
				"+49 851 509-2421", "dimitri-golovko@uni-passau.de",
				"Externer Doktorand", cg10));

		ContactGroup cg11 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Marketing und Services",
				null);
		contactPersonDAO.createContactGroup(cg11);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Totzek, Dirk, Prof. Dr.",
				"WiWi, Raum 115", "+49(0)851/509-3260",
				"Dirk.Totzek@uni-passau.de", "Professor", cg11));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Langer, Erika", "WiWi, Raum 116",
				"+49(0)851/509-3261", "marketing-services@uni-passau.de",
				"Vorzimmer", cg11));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Bergmeier, Markus",
				"WiWi, Raum 117", "+49(0)851/509-3266",
				"Markus.Bergmeier@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg11));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Frantz, Martin", "ZB, Raum 013",
				"+49(0)851/509-3264", "Martin.Frantz@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg11));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Leinsle, Philipp", "ZB, Raum 013",
				"+49(0)851/509-3263", "Philipp.Leinsle@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg11));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Maar, Daniel", "WiWi, Raum 117",
				"+49(0)851/509-3265", "Daniel.Maar@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg11));

		ContactGroup cg12 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Organisation, Technologiemanagement und Entrepreneurship",
				null);
		contactPersonDAO.createContactGroup(cg12);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Carolin Häussler",
				"WiWi, Raum 112", "0851 - 509-2481",
				"carolin.haeussler@uni-passau.de", "Lehrstuhlinhaberin", cg12));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Ulrike Ammer", "WiWi, Raum 111",
				"0851 - 509-2481", "ulrike.ammer@uni-passau.de", "Sekretariat",
				cg12));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Patrick Figge, M. Sc.",
				"WiWi, Raum 113", "0851 - 509-2484",
				"Patrick.Figge@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg12));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Bastian Rake", "WiWi, Raum 114",
				"0851 - 509-2483", "bastian.rake@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg12));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Kffr. Sabrina Vieth",
				"WiWi, Raum 113", "0851 - 509-2484",
				"sabrina.vieth@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg12));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Florian Podewils, M.A.", null, null,
				"florian.podewils@gmail.com", "Wissenschaftlicher Mitarbeiter",
				cg12));
		contactPersonDAO
				.createContactPerson(new ContactPerson(UUID.randomUUID()
						.toString(), "Sowmya Raja, B.Sc.", null, null,
						"sowmya310@gmail.com",
						"Wissenschaftliche Mitarbeiterin", cg12));

		ContactGroup cg13 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Produktion und Logistik",
				null);
		contactPersonDAO.createContactGroup(cg13);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Univ.-Prof. Dr. Hans Ziegler",
				"Jur, Raum 115", "+49 (0)851/ 509 - 2450",
				"hans.ziegler@uni-passau.de", "Lehrstuhlinhaber", cg13));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Susanne Fritsch", "Jur, Raum 114",
				"+49 (0)851/ 509 - 2451", "sekprodlog@uni-passau.de",
				"Sekretariat", cg13));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Nikolai Holeczek, M.Sc.",
				"Jur, Raum 106", "+49 (0)851/ 509 - 2454",
				"nikolai.holeczek@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg13));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(),
				"Herr Prof. Dr. Chandrasekharan Rajendran", "Jur, Raum 106",
				"+49 (0)851/ 509 - 2454", "craj@iitm.ac.in",
				"Gastwissenschaftler", cg13));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Frau Shalini Velappan",
				"Jur, Raum 107", "+49 (0)851/ 509 - 2453", null,
				"Gastwissenschaftlerin", cg13));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Frau Niventhitha Santhanama",
				"Jur, Raum 107", "+49 (0)851/ 509 - 2453", null,
				"Gastwissenschaftlerin", cg13));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Frau Sabita Devarajulu",
				"Jur, Raum 107", "+49 (0)851/ 509 - 2453", null,
				"Gastwissenschaftlerin", cg13));

		ContactGroup cg14 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Taxation (Betriebswirtschaftliche Steuerlehre)",
				null);
		contactPersonDAO.createContactGroup(cg14);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Markus Diller",
				"WiWi, Raum 208", "+49 (0)851 / 509-2440",
				"markus.diller@uni-passau.de", "Lehrstuhlinhaber/Prodekan",
				cg14));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Diana Königseder", "WiWi, Raum 207",
				"+49 (0)851 / 509-2441", "diana.koenigseder@uni-passau.de",
				"Sekretariat", cg14));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Daniela Kühne, M.Sc.",
				"WiWi, Raum 220", "+49 (0)851 / 509-2443",
				"daniela.kuehne@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg14));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Kfm. Dr. Markus Grottke",
				"WiWi, Raum 220", "+49 (0)851 / 509-2443",
				"markus.grottke@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg14));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dipl.-Kffr. Tasja Klotzkowski",
				"WiWi, Raum 209", "+49 (0)851 / 509-2445",
				"tasja.klotzkowski@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg14));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Maximilian Kittl, M.Sc.",
				"WiWi, Raum 209", "+49 (0)851 / 509-2445",
				"maximilian.kittl@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg14));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Johannes Lorenz, M.Sc., M.A.",
				"WiWi, Raum 220", "+49 (0)851 / 509-2443",
				"johannes.lorenz@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg14));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. em. Dr. Klaus D. Haase",
				"WiWi, Raum 106", "+49 (0)851 / 509-2408",
				"klaus.haase@uni-passau.de", "Emeritus", cg14));

		ContactGroup cg15 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Betriebswirtschaftslehre mit Schwerpunkt Technologie, Innovation und Entrepreneurship",
				null);
		contactPersonDAO.createContactGroup(cg15);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Andreas König",
				"HK 14b, Raum 201", "0851/509-2511",
				"andreas.koenig@uni-passau.de", "Lehrstuhlinhaber", cg15));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Ilonka Weinberger",
				"HK 14b, Raum 204", "0851/509-2511",
				"ilonka.weinberger@uni-passau.de", "Sekretariat", cg15));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Angela Fehn M.Sc.",
				"HK 14b, Raum 203", "0851/509-2513",
				"angela.fehn@uni-passau.de", "Wissenschaftliche Mitarbeiterin",
				cg15));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Michael Wiedermann M.Sc.",
				"HK 14b, Raum 203", "0851/509-2514",
				"michael.wiedermann@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg15));

		ContactGroup cg16 = new ContactGroup(UUID.randomUUID().toString(),
				"Lehrstuhl für Development Economics", null);
		contactPersonDAO.createContactGroup(cg16);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Michael Grimm",
				"ZB, Raum 158", "+49 (0)851 509-3310", "DevEcon@uni-passau.de",
				"Lehrstuhlinhaber", cg16));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Christiana Augsburg",
				"ZB, Raum 157", "+49 (0)851 509-3311",
				"christiana.augsburg@uni-passau.de", "Sekretariat", cg16));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Renate Hartwig", "ZB, Raum 155",
				"+49 (0)851 509-3314", "renate.hartwig@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg16));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Rita Motzigkeit Gonzalez",
				"ZB, Raum 155", "+49 (0)851 509-3314",
				"rita.motzigkeit-gonzalez@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg16));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Anna Luisa Paffhausen",
				"ZB, Raum 156", "+49 (0)851 509-3313",
				"anna.paffhausen@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg16));

		ContactGroup cg17 = new ContactGroup(UUID.randomUUID().toString(),
				"Lehrstuhl für International Economics", null);
		contactPersonDAO.createContactGroup(cg17);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Hans-Jörg Schmerer",
				"HK 14b, Raum 212", "+49 (0)851 509-2530",
				"Hans-Joerg.Schmerer@uni-passau.de", "Professor", cg17));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Joscha Beckmann",
				"HK 14b, Raum 212", "+49 (0)851 509-2530",
				"joscha.beckmann@uni-passau.de", "Professor", cg17));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Elisabeth Riesinger",
				"HK 14b, Raum 211", "+49 (0)851 509-2531",
				"elisabeth.riesinger@uni-passau.de", "Sekretariat", cg17));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Erwin Winkler", null, null,
				"winkle36@stud.uni-passau.de", "Mitarbeiter", cg17));

		ContactGroup cg18 = new ContactGroup(UUID.randomUUID().toString(),
				"Lehrstuhl für Statistik", null);
		contactPersonDAO.createContactGroup(cg18);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Harry Haupt",
				"WiWi, Raum 215", "+49 (0)851 509-2560",
				"harry.haupt@uni-passau.de", "Lehrstuhlinhaber", cg18));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Franziska Christoph",
				"WiWi, Raum 216", "+49 (0)851 509-2561",
				"franziska.christoph@uni-passau.de", "Sekretariat", cg18));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Joachim Schnurbus",
				"WiWi, Raum 217", "+49 (0)851 509-2563",
				"joachim.schnurbus@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg18));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Angelika Schmid", "WiWi, Raum 218",
				"+49 (0)851 509-2564", "angelika.schmid@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg18));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Sandra Huber, M.Sc",
				"WiWi, Raum 218", "+49 (0)851 509-2564",
				"sandra.huber@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg18));

		ContactGroup cg19 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Volkswirtschaftslehre mit Schwerpunkt Wirtschaftspolitik",
				null);
		contactPersonDAO.createContactGroup(cg19);
		contactPersonDAO
				.createContactPerson(new ContactPerson(UUID.randomUUID()
						.toString(), "Prof. Dr. Stefan Bauernschuster",
						"WiWi, Raum 206", "+49 (0)851 509-2540",
						"Stefan.Bauernschuster@uni-passau.de",
						"Lehrstuhlinhaber", cg19));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Angelika Wacker", "WiWi, Raum 205",
				"+49 (0)851 509-2541", "Angelika.Wacker@uni-passau.de",
				"Sekretariat", cg19));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Katrin Huber", "WiWi, Raum 221",
				"+49 (0)851 509-2544", "Katrin.Huber@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg19));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Ramona Molitor", "WiWi, Raum 219",
				"+49 (0)851 509-2543", "Ramona.Molitor@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg19));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Gerhard D. Kleinhenz",
				null, "0851/55770", "g.h.kleinhenz@t-online.de", "Emeritus",
				cg19));

		ContactGroup cg20 = new ContactGroup(
				UUID.randomUUID().toString(),
				"Lehrstuhl für Volkswirtschaftslehre mit Schwerpunkt Wirtschaftstheorie",
				null);
		contactPersonDAO.createContactGroup(cg20);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Johann Graf Lambsdorff",
				"WiWi, Raum 108", "+49 (0)851 509-2551",
				"jlambsd@uni-passau.de", "Lehrstuhlinhaber", cg20));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Fr. Ingrid Scheungraber",
				"WiWi, Raum 107", "+49 (0)851 509-2551",
				"ingrid.scheungraber@uni-passau.de", "Lehrstuhlinhaber", cg20));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(),
				"Marcus Antonio Giamattei, M.A. & B.Sc.", "WiWi, Raum 106",
				"+49 (0)851 509-2553", "marcus.giamattei@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg20));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(),
				"Isabelle Anaïs Véronique Rivière, B.Sc.", "WiWi, Raum 106",
				"+49 (0)851 509-2553", "isabelle.riviere@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg20));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Manuel Schubert",
				"WiWi, Raum 118", "+49 (0)851 509-2555",
				"manuel.schubert@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg20));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Katharina Werner, M.A. & B.Sc.",
				"WiWi, Raum 118", "+49 (0)851 509-2555",
				"katharina.werner@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg20));

		ContactGroup cg21 = new ContactGroup(UUID.randomUUID().toString(),
				"Lehrstuhl für Wirtschaftsinformatik I", null);
		contactPersonDAO.createContactGroup(cg21);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Peter Kleinschmidt",
				"Jur, Raum 010", "+49 (0)851 509-2570",
				"kleinschmidt@uni-passau.de", "Lehrstuhlinhaber", cg21));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Andreas Pfeifer",
				"Jur, Raum 003", "+49 (0)851 509-2577",
				"andreas.pfeifer@uni-passau.de", "Honorarprofessor", cg21));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Christine Klössinger",
				"Jur, Raum 012", "+49 (0)851 509-2577",
				"kloessinger@winf.uni-passau.de", "Sekretariat", cg21));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Hans Achatz", "Jur, Raum 013",
				"+49 (0)851 509-2574", "achatz@winf.uni-passau.de",
				"Akademischer Oberrat", cg21));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Alexander Keller, M.Sc.",
				"Jur, Raum 014", "+49 (0)851 509-2573",
				"keller@winf.uni-passau.de", "Wissenschaftlicher Mitarbeiter",
				cg21));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(),
				"Dipl.-Kfm. Sebastian Schinkinger, B.Sc.", "Jur, Raum 014",
				"+49 (0)851 509-2573", "schinkinger@winf.uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg21));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Max Weber, M.Sc.", "Jur, Raum 002",
				"+49 (0)851 509-2576", "weber@winf.uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg21));

		ContactGroup cg22 = new ContactGroup(UUID.randomUUID().toString(),
				"Lehrstuhl für Wirtschaftsinformatik II", null);
		contactPersonDAO.createContactGroup(cg22);
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Prof. Dr. Franz Lehner",
				"ITZ, Raum 262", "+49 (0)851 509-2590",
				"franz.lehner@uni-passau.de", "Lehrstuhlinhaber", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Claudia Reitmayer", "ITZ, Raum 261",
				"+49 (0)851 509-2591", "claudia.reitmayer@uni-passau.de",
				"Vorzimmer", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Nadine Amende", "ITZ, Raum 260",
				"+49 (0)851 509-2593", "nadine.amende@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Dr. Verena Dorner", "ITZ, Raum 255",
				"+49 (0)851 509-2416", "verena.dorner@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Nora Fteimi", "ITZ, Raum 260",
				"+49 (0)851 509-2593", "Nora.Fteimi@uni-passau.de",
				"Wissenschaftliche Mitarbeiterin", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Michael Langbauer", "ITZ, Raum 256",
				"+49 (0)851 509-2596", "Michael.Langbauer@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Florian Spindler", "ITZ, Raum 258",
				"+49 (0)851 509-2594", "Florian.Spindler@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Julian Windscheid", "ITZ, Raum 256",
				"+49 (0)851 509-2596", "Julian.Windscheid@uni-passau.de",
				"Wissenschaftlicher Mitarbeiter", cg22));
		contactPersonDAO.createContactPerson(new ContactPerson(UUID
				.randomUUID().toString(), "Katharina Lasinger", null, null,
				"Katharina.Lasinger@uni-passau.de",
				"Wissenschaftliche Hilfskraft", cg22));

		result += "done";
		return result;
	}

}