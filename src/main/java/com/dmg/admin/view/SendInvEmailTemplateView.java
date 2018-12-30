package com.dmg.admin.view;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.xpath.operations.Bool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dmg.admin.service.SendEmailService;
import com.dmg.admin.ui.ComponentUtil;
import com.dmg.admin.ui.SendMailsForm;
import com.dmg.admin.ui.SendTemplateMailsForm;
import com.dmg.admin.util.MailTemplatesGenerateUtil;
import com.dmg.admin.util.PropertiesManager;
import com.dmg.core.bean.BeansFactory;
import com.dmg.core.bean.Constants;
import com.dmg.core.bean.SendInv;
import com.dmg.core.bean.UserAccount;
import com.dmg.core.exception.DataAccessLayerException;
import com.dmg.core.persistence.FacadeFactory;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class SendInvEmailTemplateView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(SendInvEmailTemplateView.class);
	private final Navigator navigator;
	public static final String NAME = "sendEmailTemplate";
	private final SendEmailService sendEmailService;
	private String id;
	// private SendInv userAccount;
	private Panel panel;
	private Button startSendBtn;
	private SendTemplateMailsForm sendTemplateMailForm;

	public SendInvEmailTemplateView(Navigator navigator) {
		this.navigator = navigator;
		sendEmailService = new SendEmailService();
		setSizeFull();
		setSpacing(true);
		setMargin(true);
		initView();
	}

	private void initView() {
		panel = new Panel("Send Template Email Form");
		panel.setWidth("35%");
		panel.setHeight("100%");
		addComponent(ComponentUtil.initMenuButton(navigator, StartView.NAME, "Go back to main view"));
		addComponent(panel);
		setExpandRatio(panel, 0.95F);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
		startSendBtn = new Button("Send Start");
		startSendBtn.addClickListener(new ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

				try {
					if (validateFields()) {

						// sendMailForm.getBinder().commit();

						String city = sendTemplateMailForm.getCityField().getValue().toString();
						String company = sendTemplateMailForm.getCompanyField().getValue().toString();
						String buildingCode = sendTemplateMailForm.getBuildingNo().getValue();
						String accountNo = sendTemplateMailForm.getAccountNo().getValue();
						String templateField = sendTemplateMailForm.getTemplateField().getValue().toString();
						String messageText = sendTemplateMailForm.getMessageText().getValue();
						String messageTitle = sendTemplateMailForm.getTitleField().getValue();
						Boolean nameCheck = sendTemplateMailForm.getNameCheck().getValue();
						Boolean isActive = sendTemplateMailForm.getIsActive().getValue();
						Boolean isDuserExclude = sendTemplateMailForm.getIsDUserExelude().getValue();
						Boolean onlyDuser = sendTemplateMailForm.getOnlyDUser().getValue();
						Boolean isCuserExclude = sendTemplateMailForm.getIsCUserExelude().getValue();
						Boolean onlyCuser = sendTemplateMailForm.getOnlyCUser().getValue();
						Boolean onlyOnlieUser = sendTemplateMailForm.getOnlineEnableUser().getValue();
						// Boolean isBalanceMore =
						// sendTemplateMailForm.getIsBalaneMore().getValue();
						String isBalanceMoreThan = sendTemplateMailForm.getIsBalaneMore().getValue();
						Boolean contractNoCheck = sendTemplateMailForm.getContractNoCheck().getValue();
						Boolean passwordCheck = sendTemplateMailForm.getPasswordCheck().getValue();
						Boolean amountCheck = sendTemplateMailForm.getAmountCheck().getValue();
						Boolean apartmentCheck = sendTemplateMailForm.getApartmentCheck().getValue();
						Boolean buildingCheck = sendTemplateMailForm.getBuildingCheck().getValue();
						

						// File mapFile = new File(mapFilePath);

						UserAccount user = BeansFactory.getInstance().getUserAccount(city);
						Map<String, Object> parameters = new HashMap<String, Object>();

						if (!buildingCode.trim().equalsIgnoreCase("all")) {
							parameters.put(Constants.USER_BUILDING_NO, buildingCode.trim());
						}
						if (!accountNo.trim().equalsIgnoreCase("all")) {
							parameters.put(Constants.USER_ACCOUNT_ID, accountNo.trim());
						}
						parameters.put(Constants.USER_CITY, city);
						parameters.put(Constants.USER_COMPANY, company);
						parameters.put(Constants.USER_ENABLE, isActive);

						List<? extends UserAccount> list = FacadeFactory.getFacade().list(user.getClass(), parameters);
						addrecordstoDB(list, city, company, templateField, messageText, messageTitle, isBalanceMoreThan, nameCheck, contractNoCheck, passwordCheck, amountCheck, apartmentCheck, buildingCheck, isDuserExclude, onlyDuser, isCuserExclude, onlyCuser, onlyOnlieUser);

						// String[] contracts = {"D01001" , "D01004" , "D01006"
						// , "D01007" , "D01009" , "D01013" , "D01017" ,
						// "D01018" , "D01019" , "D01020" , "D01022" , "D01023"
						// , "D01026" , "D01028" , "D01029" , "D01030" ,
						// "D01032" , "D01034" , "D01036" , "D01037" , "D01041"
						// , "D01044" , "D01047" , "D01048" , "D01050" ,
						// "D01051" , "D01053" , "D01055" , "D01056" , "D01063"
						// , "D01064" , "D01065" , "D01069" , "D01070" ,
						// "D01073" , "D01075" , "D01076" , "D01077" , "D01083"
						// , "D01086" , "D01087" , "D01088" , "D01090" ,
						// "D01091" , "D01093" , "D01106" , "D01108" , "D01110"
						// , "D01111" , "D01112" , "D01113" , "D01114" ,
						// "D01116" , "D01118" , "D01119" , "D01120" , "D01121"
						// , "D01122" , "D01123" , "D01124" , "D01125" ,
						// "D01128" , "D01129" , "D01130" , "D01131" , "D01132"
						// , "D01135" , "D01136" , "D01137" , "D01138" ,
						// "D01140" , "D01141" , "D01142" , "D01148" , "D01149"
						// , "D01150" , "D01152" , "D01153" , "D01155" ,
						// "D01157" , "D01158" , "D01161" , "D01165" , "D01167"
						// , "D01168" , "D01169" , "D01172" , "D01173" ,
						// "D01174" , "D01175" , "D01177" , "D01178" , "D01180"
						// , "D01181" , "D01182" , "D01183" , "D01185" ,
						// "D01187" , "D01188" , "D01190" , "D01191" , "D01192"
						// , "D01193" , "D01194" , "D01195" , "D01197" ,
						// "D01198" , "D01200" , "D01201" , "D01202" , "D01203"
						// , "D01205" , "D01206" , "D01207" , "D01209" ,
						// "D01210" , "D01211" , "D01212" , "D01213" , "D01214"
						// , "D01218" , "D01219" , "D01220" , "D01222" ,
						// "D01223" , "D01224" , "D01225" , "D01231" , "D01232"
						// , "D01233" , "D01234" , "D01235" , "D01238" ,
						// "D01240" , "D01241" , "D01242" , "D01244" , "D01245"
						// , "D01251" , "D01252" , "D01253" , "D01256" ,
						// "D01262" , "D01263" , "D01269" , "D01270" , "D01275"
						// , "D01277" , "D01282" , "D01284" , "D01285" ,
						// "D01290" , "D01291" , "D01293" , "D01295" , "D01297"
						// , "D01298" , "D01299" , "D01301" , "D01302" ,
						// "D01303" , "D01305" , "D01306" , "D01307" , "D01309"
						// , "D01310" , "D01312" , "D01313" , "D01315" ,
						// "D01316" , "D01318" , "D01319" , "D01322" , "D01329"
						// , "D01331" , "D01332" , "D01334" , "D01336" ,
						// "D01338" , "D01339" , "D01340" , "D01342" , "D01343"
						// , "D01346" , "D01347" , "D01348" , "D01349" ,
						// "D01354" , "D01359" , "D01360" , "D01364" , "D01369"
						// , "D01370" , "D01371" , "D01373" , "D01374" ,
						// "D01375" , "D01377" , "D01378" , "D01379" , "D01380"
						// , "D01382" , "D01383" , "D01384" , "D01385" ,
						// "D01386" , "D01388" , "D01390" , "D01391" , "D01394"
						// , "D01396" , "D01397" , "D01400" , "D01401" ,
						// "D01402" , "D01403" , "D01407" , "D01409" , "D01410"
						// , "D01411" , "D01413" , "D01414" , "D01416" ,
						// "D01417" , "D01418" , "D01419" , "D01422" , "D01423"
						// , "D01424" , "D01425" , "D01426" , "D01427" ,
						// "D01428" , "D01430" , "D01433" , "D01437" , "D01438"
						// , "D01440" , "D01441" , "D01442" , "D01444" ,
						// "D01445" , "D01446" , "D01447" , "D01448" , "D01449"
						// , "D01450" , "D01453" , "D01459" , "D01464" ,
						// "D01465" , "D01466" , "D01467" , "D01469" , "D01471"
						// , "D01472" , "D01473" , "D01476" , "D01477" ,
						// "D01478" , "D01480" , "D01481" , "D01482" , "D01483"
						// , "D01484" , "D01485" , "D01486" , "D01487" ,
						// "D01492" , "D01493" , "D01494" , "D01496" , "D01498"
						// , "D01499" , "D01500" , "D01501" , "D01502" ,
						// "D01508" , "D01516" , "D01520" , "D01521" , "D01525"
						// , "D01526" , "D01529" , "D01533" , "D01535" ,
						// "D01537" , "D01539" , "D01540" , "D01542" , "D01550"
						// , "D01553" , "D01555" , "D01556" , "D01560" ,
						// "D01562" , "D01565" , "D01569" , "D01571" , "D01572"
						// , "D01574" , "D01575" , "D01577" , "D01580" ,
						// "D01581" , "D01582" , "D01583" , "D01586" , "D01587"
						// , "D01588" , "D01589" , "D01590" , "D01592" ,
						// "D01593" , "D01595" , "D01598" , "D01599" , "D01600"
						// , "D01607" , "D01610" , "D01611" , "D01614" ,
						// "D01615" , "D01616" , "D01617" , "D01622" , "D01623"
						// , "D01624" , "D01628" , "D01631" , "D01634" ,
						// "D01638" , "D01640" , "D01641" , "D01644" , "D01646"
						// , "D01650" , "D01652" , "D01653" , "D01657" ,
						// "D01659" , "D01662" , "D01667" , "D01668" , "D01672"
						// , "D01674" , "D01677" , "D01678" , "D01687" ,
						// "D01688" , "D01689" , "D01690" , "D01691" , "D01693"
						// , "D01695" , "D01697" , "D01698" , "D01699" ,
						// "D01700" , "D01701" , "D01703" , "D01707" , "D01708"
						// , "D01710" , "D01711" , "D01712" , "D01713" ,
						// "D01717" , "D01720" , "D01721" , "D01722" , "D01724"
						// , "D01725" , "D01727" , "D01728" , "D01730" ,
						// "D01731" , "D01734" , "D01735" , "D01736" , "D01738"
						// , "D01741" , "D01743" , "D01746" , "D01747" ,
						// "D01749" , "D01752" , "D01753" , "D01754" , "D01755"
						// , "D01756" , "D01757" , "D01759" , "D01760" ,
						// "D01761" , "D01764" , "D01765" , "D01766" , "D01767"
						// , "D01768" , "D01770" , "D01772" , "D01773" ,
						// "D01774" , "D01776" , "D01777" , "D01778" , "D01779"
						// , "D01780" , "D01785" , "D01786" , "D01787" ,
						// "D01789" , "D01793" , "D01794" , "D01796" , "D01797"
						// , "D01798" , "D01799" , "D01806" , "D01809" ,
						// "D01810" , "D01812" , "D01813" , "D01814" , "D01815"
						// , "D01816" , "D01817" , "D01818" , "D01820" ,
						// "D01821" , "D01823" , "D01824" , "D01825" , "D01826"
						// , "D01827" , "D01828" , "D01829" , "D01831" ,
						// "D01835" , "D01836" , "D01837" , "D01841" , "D01843"
						// , "D01847" , "D01849" , "D01851" , "D01852" ,
						// "D01853" , "D01854" , "D01855" , "D01856" , "D01857"
						// , "D01858" , "D01860" , "D01863" , "D01864" ,
						// "D01867" , "D01868" , "D01870" , "D01872" , "D01873"
						// , "D01876" , "D01880" , "D01882" , "D01883" ,
						// "D01884" , "D01885" , "D01886" , "D01887" , "D01889"
						// , "D01890" , "D01891" , "D01898" , "D01900" ,
						// "D01901" , "D01903" , "D01905" , "D01906" , "D01909"
						// , "D01910" , "D01911" , "D01915" , "D01916" ,
						// "D01918" , "D01920" , "D01923" , "D01924" , "D01928"
						// , "D01929" , "D01931" , "D01932" , "D01934" ,
						// "D01935" , "D01936" , "D01937" , "D01938" , "D01939"
						// , "D01942" , "D01943" , "D01945" , "D01947" ,
						// "D01949" , "D01950" , "D01951" , "D01954" , "D01955"
						// , "D01958" , "D01959" , "D01961" , "D01963" ,
						// "D01966" , "D01972" , "D01974" , "D01979" , "D01982"
						// , "D01984" , "D01985" , "D01986" , "D01987" ,
						// "D01988" , "D01989" , "D01990" , "D01991" , "D01992"
						// , "D01993" , "D01995" , "D01997" , "D01998" ,
						// "D01999" , "D02001" , "D02005" , "D02007" , "D02008"
						// , "D02009" , "D02011" , "D02013" , "D02014" ,
						// "D02015" , "D02018" , "D02019" , "D02022" , "D02024"
						// , "D02025" , "D02027" , "D02029" , "D02030" ,
						// "D02033" , "D02035" , "D02037" , "D02040" , "D02041"
						// , "D02043" , "D02047" , "D02051" , "D02052" ,
						// "D02053" , "D02054" , "D02056" , "D02059" , "D02060"
						// , "D02061" , "D02063" , "D02064" , "D02066" ,
						// "D02068" , "D02069" , "D02070" , "D02071" , "D02073"
						// , "D02074" , "D02075" , "D02078" , "D02079" ,
						// "D02080" , "D02082" , "D02084" , "D02085" , "D02087"
						// , "D02088" , "D02089" , "D02091" , "D02092" ,
						// "D02094" , "D02095" , "D02096" , "D02097" , "D02098"
						// , "D02100" , "D02103" , "D02105" , "D02107" ,
						// "D02108" , "D02110" , "D02112" , "D02114" , "D02115"
						// , "D02117" , "D02118" , "D02120" , "D02121" ,
						// "D02122" , "D02123" , "D02125" , "D02127" , "D02128"
						// , "D02129" , "D02133" , "D02134" , "D02135" ,
						// "D02136" , "D02139" , "D02140" , "D02142" , "D02147"
						// , "D02148" , "D02154" , "D02159" , "D02160" ,
						// "D02162" , "D02166" , "D02170" , "D02175" , "D02182"
						// , "D02186" , "D02194" , "D02196" , "D02199" ,
						// "D02200" , "D02205" , "D02206" , "D02207" , "D02208"
						// , "D02209" , "D02211" , "D02212" , "D02216" ,
						// "D02218" , "D02219" , "D02221" , "D02222" , "D02225"
						// , "D02226" , "D02227" , "D02228" , "D02229" ,
						// "D02230" , "D02231" , "D02232" , "D02234" , "D02235"
						// , "D02237" , "D02238" , "D02242" , "D02245" ,
						// "D02248" , "D02249" , "D02255" , "D02261" , "D02265"
						// , "D02266" , "D02271" , "D02279" , "D02283" ,
						// "D02289" , "D02300" , "D02301" , "D02302" , "D02303"
						// , "D02305" , "D02307" , "D02310" , "D02314" ,
						// "D02319" , "D02320" , "D02321" , "D02327" , "D02329"
						// , "D02332" , "D02335" , "D02336" , "D02340" ,
						// "D02341" , "D02342" , "D02343" , "D02344" , "D02345"
						// , "D02358" , "D02359" , "D02361" , "D02362" ,
						// "D02363" , "D02364" , "D02365" , "D02366" , "D02367"
						// , "D02368" , "D02369" , "D02370" , "D02371" ,
						// "D02373" , "D02374" , "D02376" , "D02378" , "D02379"
						// , "D02380" , "D02381" , "D02382" , "D02384" ,
						// "D02389" , "D02390" , "D02392" , "D02393" , "D02395"
						// , "D02401" , "D02402" , "D02403" , "D02404" ,
						// "D02406" , "D02407" , "D02409" , "D02410" , "D02414"
						// , "D02415" , "D02419" , "D02420" , "D02421" ,
						// "D02424" , "D02425" , "D02427" , "D02428" , "D02429"
						// , "D02430" , "D02432" , "D02433" , "D02435" ,
						// "D02436" , "D02438" , "D02440" , "D02442" , "D02445"
						// , "D02451" , "D02453" , "D02458" , "D02460" ,
						// "D02463" , "D02469" , "D02470" , "D02472" , "D02473"
						// , "D02474" , "D02476" , "D02477" , "D02478" ,
						// "D02479" , "D02481" , "D02482" , "D02483" , "D02484"
						// , "D02485" , "D02486" , "D02487" , "D02488" ,
						// "D02489" , "D02492" , "D02498" , "D02501" , "D02502"
						// , "D02512" , "D02515" , "D02517" , "D02519" ,
						// "D02521" , "D02522" , "D02523" , "D02526" , "D02528"
						// , "D02529" , "D02534" , "D02535" , "D02536" ,
						// "D02542" , "D02545" , "D02548" , "D02549" , "D02550"
						// , "D02551" , "D02553" , "D02555" , "D02556" ,
						// "D02558" , "D02564" , "D02569" , "D02572" , "D02574"
						// , "D02581" , "D02583" , "D02585" , "D02587" ,
						// "D02594" , "D02595" , "D02596" , "D02599" , "D02601"
						// , "D02604" , "D02605" , "D02607" , "D02611" ,
						// "D02612" , "D02613" , "D02616" , "D02617" , "D02623"
						// , "D02624" , "D02625" , "D02626" , "D02629" ,
						// "D02631" , "D02633" , "D02636" , "D02637" , "D02638"
						// , "D02639" , "D02640" , "D02647" , "D02648" ,
						// "D02653" , "D02657" , "D02660" , "D02664" , "D02671"
						// , "D02672" , "D02673" , "D02674" , "D02675" ,
						// "D02676" , "D02682" , "D02684" , "D02685" , "D02686"
						// , "D02687" , "D02688" , "D02689" , "D02691" ,
						// "D02692" , "D02695"};
						// for (String contractID : contracts) {
						// Map<String, Object> parameters2 = new HashMap<String,
						// Object>();
						// parameters2.put(Constants.USER_ACCOUNT_ID,
						// contractID.trim());
						// parameters2.put(Constants.USER_COMPANY, company);
						// List<? extends UserAccount> list =
						// FacadeFactory.getFacade().list(user.getClass(),
						// parameters2);
						// addrecordstoDB(list, city, company, templateField
						// ,messageText, messageTitle, isBalanceMore, nameCheck,
						// contractNoCheck, passwordCheck, amountCheck );
						// }
						Notification.show("User has been updated successfully!", Type.HUMANIZED_MESSAGE);

					}
				} catch (DataAccessLayerException e) {
					log.error("DataAccessLayerException", e);
					Notification.show("ERROR in reading Date From DB!", Type.ERROR_MESSAGE);
				} catch (IOException e) {
					log.error("error in coping files", e);
					Notification.show("ERROR in COPING FILES!", Type.ERROR_MESSAGE);
				}
			}

		});

	}

	private void addrecordstoDB(List<? extends UserAccount> users, String city, String company, String templateField, String messageText, String messageTitle, String isBalanceMore, Boolean nameCheck,
			Boolean contractNoCheck, Boolean passwordCheck, Boolean amountCheck, Boolean apartmentCheck, Boolean buildingCheck, Boolean isDuserExclude, Boolean onlyDuser, Boolean isCuserExclude, Boolean onlyCuser, Boolean onlyOnlieUser) throws IOException {

		// // Map<String, String> map = new HashMap<String, String>();
		// String pdfBasePath =
		// PropertiesManager.getInstance().getProperty("adnc.pdf.base.path");
		String pdfSendStatus = PropertiesManager.getInstance().getProperty("adnc.pdf.send.status");
		// if(!pdfBasePath.endsWith("/")){
		// pdfBasePath+="/";
		// }

		Date time = Calendar.getInstance().getTime();
		// SimpleDateFormat df = new SimpleDateFormat("yyyy/MM");
		// String format = df.format(time);
		// pdfBasePath+=format;
		// File dir = new File(pdfBasePath);
		// if(!dir.exists()){
		// dir.mkdirs();
		// }
		// BufferedReader reader = new BufferedReader(new FileReader(mapFile));
		// String line = reader.readLine();

		MailTemplatesGenerateUtil mailTemplateGenerator = MailTemplatesGenerateUtil.getInstance();
		for (UserAccount userAccount : users) {
			if (userAccount == null) {
				log.warn("Users Not Send becaus of missing data" + userAccount);
				continue;
			}

			// if(!userAccount.isEnable()){
			// log.warn("User Not Enabled" + userAccount.getContractNo());
			// continue;
			// }

			double parseDouble = 0.0;
			try {
				if (isBalanceMore != null && !isBalanceMore.isEmpty()) {
					parseDouble = Double.parseDouble(isBalanceMore);
					if (!(userAccount.getBalance().doubleValue() > parseDouble)) {
						log.warn("User Balance less than required " + userAccount.getContractNo());
						continue;
					}
				}
			} catch (NumberFormatException e) {
				log.error("Error barsing Balance limit");
			}
			
			
			if(isDuserExclude && userAccount.getContractNo().startsWith("D0")){
				log.warn("D User  Skiped " + userAccount.getContractNo());
				continue;
			}
			
			if(onlyDuser && !userAccount.getContractNo().startsWith("D0")){
				log.warn("Non D User  Skiped " + userAccount.getContractNo());
				continue;
			}
			
			if(isCuserExclude && userAccount.getContractNo().startsWith("C")){
				log.warn("C User  Skiped " + userAccount.getContractNo());
				continue;
			}
			
			if(onlyCuser && !userAccount.getContractNo().startsWith("C")){
				log.warn("Non C User  Skiped " + userAccount.getContractNo());
				continue;
			}

			if(onlyOnlieUser && !userAccount.isOnlineEnable()){
				log.warn("Non Online User User  Skiped " + userAccount.getContractNo());
				continue;
			}
			
			
			SendInv sendInv = new SendInv();
			sendInv.setCcbId("");
			sendInv.setCity(city);
			sendInv.setCompany(company);
			sendInv.setContractNo(userAccount.getContractNo());
			sendInv.setFileName("");
			sendInv.setCreationDate(time);
			sendInv.setStatus(pdfSendStatus); // "PENDING"
			sendInv.setPrefix("");
			String body = mailTemplateGenerator.createEmailTemplate(templateField, messageText, messageTitle, userAccount, nameCheck, contractNoCheck, passwordCheck, false, apartmentCheck, amountCheck, buildingCheck);
			sendInv.setAttachment("");
			sendInv.setTitle(messageTitle);
			sendInv.setBody(body);

			try {
				sendEmailService.store(sendInv);
			} catch (DataAccessLayerException e) {
				log.error("ERROR in save line " + userAccount.getContractNo());
				// Notification.show("ERROR in reading Mapping File!",
				// Type.ERROR_MESSAGE);
			} catch (Exception e) {
				log.error("ERROR in copyFile line " + userAccount.getContractNo());
			}
		}
		// return map;
	}

	private boolean validateFields() {

		Object city = sendTemplateMailForm.getCityField().getValue();
		Object company = sendTemplateMailForm.getCompanyField().getValue();
		String buildingNo = sendTemplateMailForm.getBuildingNo().getValue();
		String accountNo = sendTemplateMailForm.getAccountNo().getValue();
		Object template = sendTemplateMailForm.getTemplateField().getValue();
		String messageText = sendTemplateMailForm.getMessageText().getValue();

		if (city == null || city.toString().isEmpty()) {
			Notification.show("ERROR", "Please Select City !", Type.ERROR_MESSAGE);
			return false;
		}

		if (company == null || company.toString().isEmpty()) {
			Notification.show("ERROR", "Please Select Company Value !", Type.ERROR_MESSAGE);
			return false;
		}

		if (template == null || template.toString().isEmpty()) {
			Notification.show("ERROR", "Please Select Template Value !", Type.ERROR_MESSAGE);
			return false;
		}

		if (buildingNo == null || buildingNo.trim().isEmpty()) {
			Notification.show("ERROR", "Please Fill building No !", Type.ERROR_MESSAGE);
			return false;
		}

		if (accountNo == null || accountNo.trim().isEmpty()) {
			Notification.show("ERROR", "Please Fill Contract No", Type.ERROR_MESSAGE);
			return false;
		}

		if (template.toString().equalsIgnoreCase("FREE")) {
			if (messageText == null || messageText.isEmpty()) {
				Notification.show("ERROR", "message Text isn empty !", Type.ERROR_MESSAGE);
				return false;
			}
		}

		return true;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// id = event.getParameters();
		try {

			// userAccount = accountService.getUserAcount(Long.parseLong(id));

			// PropertysetItem item = new PropertysetItem();
			// item.addItemProperty("name", new
			// ObjectProperty<String>(userAccount.getName() == null ? "" :
			// userAccount.getName()));
			// item.addItemProperty("email", new
			// ObjectProperty<String>(userAccount.getEmail() == null ? "" :
			// userAccount.getEmail()));
			// item.addItemProperty("city", new
			// ObjectProperty<String>(userAccount.getCity() == null ? "" :
			// userAccount.getCity()));
			// item.addItemProperty("buildingNumber", new
			// ObjectProperty<String>(userAccount.getBuildingNumber() == null ?
			// "" : userAccount.getBuildingNumber()));
			// item.addItemProperty("apartmentNumber", new
			// ObjectProperty<String>(userAccount.getAppartmentNumber() == null
			// ? "" : userAccount.getAppartmentNumber()));
			// item.addItemProperty("contractNumber", new
			// ObjectProperty<String>(userAccount.getContractNo() == null ? "" :
			// userAccount.getContractNo()));
			// item.addItemProperty("phone", new
			// ObjectProperty<String>(userAccount.getPhone() == null ? "" :
			// userAccount.getPhone()));
			// item.addItemProperty("mobile", new
			// ObjectProperty<String>(userAccount.getMobile() == null ? "" :
			// userAccount.getMobile()));
			// item.addItemProperty("pobox", new
			// ObjectProperty<String>(userAccount.getPobox() == null ? "" :
			// userAccount.getPobox()));
			// item.addItemProperty("poboxCity", new
			// ObjectProperty<String>(userAccount.getPoboxCity() == null ? "" :
			// userAccount.getPoboxCity()));
			// item.addItemProperty("enable", new
			// ObjectProperty<Boolean>(userAccount.getEnable()));

			sendTemplateMailForm = new SendTemplateMailsForm();
			// here setting the read only fields
			sendTemplateMailForm.getLayout().addComponent(startSendBtn);
			panel.setContent(sendTemplateMailForm);

		} catch (NumberFormatException e) {
			if (e instanceof NumberFormatException) {
				Notification.show("ID entered is not correct", Type.ERROR_MESSAGE);
				navigator.navigateTo("");
			} else {
				Notification.show("DB error - Please contact system admin", Type.ERROR_MESSAGE);
			}
		}
	}
}
