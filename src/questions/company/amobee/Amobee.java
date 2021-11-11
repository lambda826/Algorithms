/**
 *  @author Yunxiang He
 *  @date 02/14/2019
 */

package questions.company.amobee;


import questions.problems.All_Path_Sum;

/*
绗竴杞?45鍒嗛挓锛岄棶绠?鍘嗭紝闂熀纭?鐭ヨ瘑鍗犵敤浜?25鍒嗛挓宸﹀彸锛屽墿涓?20鍒嗛挓鍋氶锛屼竴閬? HARD锛屽氨鏄疞EETCODE閲岄偅鍑犻亾O锛?1锛夋搷浣滅殑棰樹箣涓?锛岃寰楃暐鏈夋敼鍔ㄤ絾涓嶅ぇ锛屾垜鍒疯繃鍘熼锛屾墍浠?10鍒嗛挓鍐欏畬锛屽張鑱婁簡鑱婂ぉ锛屽氨杩囦簡绗竴杞?

绗簩杞瘮绗竴杞殧浜嗗嚑澶╋紝闈簡涓?涓灏忔椂锛岀浜岃疆涔熻?冧簡涓狧ARD锛屽熀鏈笂鏄疞C涓憳妯辨棰?  cherry pick up锛屽仛瀹岄鍚庨棶浜嗗ソ鍑犱釜澶氱嚎绋嬶紝鍒嗗竷寮忕殑鍩虹鐭ヨ瘑锛岃繕缂栦簡绋? 



浠栧鏈?杩戞敹璐簡turn锛岀幇鍦ㄥ湪鎬ュ墽鎵╁紶銆?
绗竴杞數闈紝鐩存帴鎷夸簡涓?涓猯ogin缃戦〉杩囨潵锛屼竴鍫唄tml锛宑ss锛? jquery浠ｇ爜锛岄噷闈竴鍫嗛敊璇紝瀹屽叏涓峸ork锛岃姹傛敼閿欍?傛姌鑵句簡涓?涓嬩互鍚庯紝鏀瑰嚭鏉ヤ簡锛宩query鑰冪偣1 submit鍜宑lick鐨勫尯鍒? 2 ajax call
绗簩杞簵闈細鍏堣亰鍩虹鐭ヨ瘑锛屽墠鍚庣閮芥湁銆傝寰楃殑鏈塭vent bubbling锛? event capturing銆傛渶鍚庢槸涓?閬撳緢绠?鍗曠畻娉曢锛歮erge two sorted array銆?
鍚庢潵灏辫仈绯籵nsite浜嗭紝鍦╫nsite涔嬪墠锛岀粍閲岀殑manager鐩存帴缁欐垜鎵撲簡涓?鐢佃瘽锛屾槸涓笁鍝ワ紝闂垜杩樺湪闈㈠叾浠栧摢浜涘叕鍙稿悧锛屾垜璇存槸鐨勶紝鐒跺悗浠栧氨闂叿浣撳摢浜涘叕鍙革紝鎴戣涓嶆柟渚块?忛湶锛屼粬纭?兼垜璇达紝鐒跺悗杩樿杩欎釜涓嶄細褰卞搷鍒颁綘鐨勯潰璇曪紝鐒跺悗鎴戝氨璇翠簡锛岀粨鏋滀粬椹笂灏变笉楂樺叴浜嗭紝璇存垜鐜板湪杩樹笉鑳藉喅瀹氳兘涓嶈兘缁欎綘onsite锛屽洜涓烘ゼ涓婚潰鐨勫叾浠栧叕鍙搁兘鏄ぇ鍏徃锛屽彧鏈変粬浠叕鍙告瘮杈冨皬锛屼粬瑙夊緱鍗充娇浠栫粰浜唎ffer鎴戜篃涓嶄細鍘伙紝
妤间富褰撳満灏卞緢涓嶇埥锛岃繖涓笁鍝ュお闃撮櫓锛屾湁浜嗚繖绉嶈?佹澘锛屼互鍚庢棩瀛愭病鍙戣繃銆備竴澶╀箣鍚庯紝浠栫粰鎴戦偖浠惰鎴戝彲浠ョ粰浣爋nsite锛屼絾鏄鏋滅粰浣爋ffer锛屼綘蹇呴』褰撳満鍐冲畾鏄惁鏉ワ紝鎴戝績閲屽氨鍛靛懙浜嗭紝璧板ソ涓嶉?併??


Info session涓婅亰澶╄鏈塷pening锛屾劅瑙変粬浠鍋氱殑涓滆タ寰堟湁impact鑰屼笖鎶?鏈篃寰堟柊锛岀害涓?鍛ㄥ悗鐢甸潰锛岀儥鍗皊enior銆?
鑱婄畝鍘嗙殑椤圭洰锛岄」鐩殑鍚庡彴璁捐瀹炵幇锛岀敤杩囦粈涔堝垎甯冨紡鎶?鏈紝涓?浜涘熀纭?鐭ヨ瘑闂瓟銆?
coding棰?: Reverse String (on many machines)
follow-up 1: very big string
follow-up 2: 浠?2鍙版満鍣ㄥ埌寰堝鍙版満鍣ㄦ?庝箞split鎬庝箞merge
-baidu 1point3acres
follow-up 3: 闈炲父澶氬彴鏈哄櫒骞冲潎鏈?10%鐨別rror rate鎬庝箞瑙ｅ喅
闈㈠畬鍚庣瓑浜?2鍛ㄦ病娑堟伅锛宮ove on. 浠栧杩欎釜宀椾綅瀵瑰垎甯冨紡鐨勮姹傝繕鏄緢楂樼殑锛屾病鏈夌浉鍏崇殑瀹炰範鎴杝erious project闂畝鍘嗗氨闂灝浜嗐?侶R杩樹笉閿欙紝涓棿reschedule浜?2娆″洖澶嶉兘寰堝揩銆備絾鏄潰璇曠殑鏃跺?欐劅瑙夌儥鍗伴潰璇曞畼鏈夌偣涓嶈?愮儲銆?




涔嬪墠鍦ㄥ湴閲屽彂杩囦粬浠鐨勭數闈㈤潰缁忥紝涓?寮?濮嬫劅瑙変粬浠殑manager浜轰笉澶ソ锛屽氨鍏坔old杩欎釜onsite浜嗭紝鍚庢潵manager鍙堟潵鑱旂郴鎴戯紝鎵?浠ュ氨杩樻槸鍘籵nsite闈簡涓?涓嬨?傚厛鏉ヨ繖閲岀畝鍗曚粙缁嶄竴涓媋mobee杩欎釜鍏徃锛屼粬浠浠婂勾鍥涙湀鏀惰喘浜唗urn锛屽湪蹇?熸墿寮犱腑锛岀幇鍦ㄥ湪series D銆傚叕鍙镐綅浜巖edwood downtown锛岃繖涓叕鍙稿崰浜嗕竴鏍嬫ゼ涓殑涓ゅ眰銆傚唴閮ㄧ幆澧冭繕涓嶉敊锛屾槸閭ｇopen space銆傚懆涓?鍒板懆鍥涙湁鍏嶈垂鐨勫崍楗紝鐒跺悗杩樻湁鍚勭楗枡鍜屽悆鐨勩??
涓嬮潰璇磋闈㈣瘯锛?
Round1:鏄竴涓簹瑁旂殑澶у彅锛屼汉寰坣ice锛屼篃鏄垰璺宠繃鍘荤殑銆傚嚭浜嗕竴閬撳拰浠栦滑鍏徃涓氬姟寰堢浉鍏崇殑闂锛屾槸杩欐牱瀛愮殑锛屾湁涓や釜class锛歛ccount鍜宲ayment model, 璁╂垜璁捐杩欎袱涓猚lass瀹炵幇account receives monthly payment according to different payment model. 鎵?浠ョ粡杩囪璁哄湪account涓紝鏈塨alance锛宎ccount Number, payment model, due date绛塿ariable锛岀劧鍚庡湪payment
model涓紝 鏈塻ervice plan锛? fee锛? expiration date绛夈?傝繖浜涘熀鏈殑涓滆タ璁捐濂戒互鍚庯紝瑕佹眰鍐欎竴涓猰ontly payment鐨刦unction锛宖unction鐨勮緭鍏ユ槸a list of account锛岀劧鍚庤繕瑕佹眰鏍规嵁payment model鐨勭绫绘潵sort account銆? 鐒跺悗杩樻湁涓?浜沠ollow up锛屾湁鐐逛笉璁板緱浜?
Round2锛氬崕浜哄皬鍝ワ紝缁勯噷鐨刲ead锛屽厛浣垮姴鍎块棶浜嗕竴閬嶇畝鍘嗭紝寰堢粏锛岃繕闂簡gruntfile鐨勪笢瑗匡紝娌℃湁绛斿ソ銆傜劧鍚庤姹傚啓涓?涓墠绔殑typeahead锛宎pi鍐欏ソ浜嗭紝鐩存帴call灏辫銆傛垜鍩烘湰閮藉啓濂戒互鍚庯紝闂?庝箞瑙ｅ喅typeahead race鐨勯棶棰橈紝杩樻湁濡傛灉杩斿洖鐨剆earch result杩囧锛屾垜浠簲璇ユ?庝箞澶勭悊銆?
Round3: 鍜宮anager鍗堥キ+鎶?鏈潰銆傝浜嗛潰浠ュ悗鎰熻manager浜鸿繕鏄緢濂界殑锛屾尯鐑儏鐨勩?? 鍗堥キ鐨勬椂鍊欓棶浜嗗嚑涓妧鏈棶棰橈紝browser閲岄潰type浜唘rl浠ュ悗锛屼細鍙戠敓浠?涔堬紝鎶婃墍鐭ラ亾鐨勫叏閮ㄨ浜嗕竴閬嶏紝鍚庢潵鍙堥棶浜嗗鏋滆璁′竴涓獃outube锛屽湪browser绔拰server绔殑storage璇ユ?庝箞璁捐銆傝繖涓病鏁存槑鐧姐??
Round4: 涓?寮?濮嬬湅鍚嶅瓧鍍忔槸涓笁鍝ワ紝鍚庢潵瑙佸埌鏈汉鍍忔槸涓狝BI锛岀洿鎺ヤ袱閬撶畻娉曢锛屼紴琛ｅ拰鍎挎皵鍎裤??
闈㈠畬浜嗕互鍚庯紝manager 鏀堕泦浜嗕竴涓媐eedback锛屽氨缁欏彛澶磑ffer浜嗐?? 



鍙堟槸涓?鍫嗗墠绔粏鑺傦紝杩樻湁涓?浜涘叧浜巇esign patterns鐨勫簲鐢?
coding: 缁欎竴娈礽nt array锛屾?荤粨range杈撳嚭锛屾湁涓?浜涘皬bug鏈?鍚巉ix浜?-baidu 1point3acres
鍒氬垰鏀跺埌璺簡锛屼笉鏅撳緱杩欎釜鍏徃浠?涔堥銆傘??


闈㈣瘯閮界粨鏉燂紝鍙戜竴涓咕鍖哄叕鍙? Amobee 鐨勯潰缁忥紝宀椾綅鏄痳esearch engineer, 杩欏鍏徃涓昏鍋? DSP 骞垮憡骞冲彴 (Demand side platform)銆?
涓?鍏?2杞簵闈紝鍜?3杞? remote onsite锛岄鐩毦搴︿笉澶э紝浣撻獙寰堝ソ锛岄潰璇曞畼鍜孒R 閮藉緢nice锛屾垜闈㈢殑缁勫ぇ澶氭槸鍥戒汉hhh銆?
闈㈣瘯閲嶇偣閮芥槸鍦? coding 鍜? machine learning 鐨勫熀纭?鐭ヨ瘑锛屼緥濡? Findduplicate substring(fixed length k) in a string锛? Lowestcommon ancestor 绛夈??
鎬讳綋鎰熻涓嶉敊锛屽ぇ瀹跺緢鏈夎瘹鎰忥紝涔熻兘浣撶幇鍦╬ackage涓? :)
Coding: 3 Sum




java static鐨勬槸浠?涔?, follow up, 閭ｅ瓨鍦ㄥ唴瀛樺摢涓?蹇?, 涓?寮?濮嬭stack, 鍚庢潵鏀规垚method area, 涓嶇煡閬撶瓟妗?
java serialization鏄敤鏉ュ共鍚楃殑
java hashmap鏄粈涔?, 澶嶆潅璇?, 鎵簡鎬庝箞瀹炵幇鐨?
java int range鏄灏?, 鑴戞娊,娌″洖绛斾笂鏉?, 鐩存帴鏄?2^16娆?, 浠ヤ负2^32娆?/2鏄?16, 鎻愰啋鍚?,鍚庢潵鏀规垚浜?2^31娆℃柟
涓?涓猘rray瀛樻斁鎵?鏈塱nterger, 闇?瑕佸灏憁emory, follow up 2 power n鎹㈢畻鎴愬ぇ姒傜殑鏁板瓧
涓?涓猙igest file in single server, how to sort -> anwer: split into small pieces->sort-> merge
闂甿erge鏃剁殑io鏃堕棿澶嶆潅搴?, 鍋囪鏈塳涓猣ile, 姣忎釜file鏈塶涓厓绱?, 缁忚繃鍙嶅鎻愰啋鍚庢槸log K * (2kn)
shell閲屽浣曟悳绱㈠瓧绗? -> grep
shell涓嶅悓server, 濡備綍浜掍紶鏁版嵁
hadoop浣犲钩鏃舵槸鐢ㄦ潵骞插悧鐨?
宸ヤ綔涓綘娌℃湁鍋氳繃map reducer鐨勪簨鎯?
鎸虹疮鐨?, 鍥犱负娌℃湁绗斿拰绾?, 浠ヤ负鍙細闂棶姒傚康, 寰堝鍙兘鍦ㄨ剳瀛愰噷璁＄畻. 1point3acres
bq闂, 浣犱汉涓轰綘鏈夊摢浜涙柟渚垮仛浜嗘瘮浣犲悓浜嬪ソ
bq闂, 鏈夋病鏈夊叾浠栫殑闈㈣瘯-> 鍥犱负鐪嬭繃闈㈢粡, 浠栧濂借薄姣旇緝care, 鐩存帴璇?, 浠栧鏄垜闈㈣繃鏈?濂界殑, 鏈?鎯冲幓鐨?
搴旇涓嶄細鏈塶ext浜?, 灏卞綋閿荤偧浜?, 閫犵鍚庨潰鐨勪汉



涓?鍫嗗墠绔痡s鐭ヨ瘑澶栧姞java hashmap:
how does browser render website content?
padding versus margin;
closure;
jquery location;
reactjs vs angularjs
coding: merge two sorted array
 */

public class Amobee {

    All_Path_Sum All_Path_Sum = null;
    questions.temp._0336_Palindrome_Pairs _0336_Palindrome_Pairs = null;
    questions._01_array._0031_Next_Permutation _0031_Next_Permutation = null;
    questions._10_tree.bst._0272_Closest_Binary_Search_Tree_Value_II _0272_Closest_Binary_Search_Tree_Value_II = null;
    questions.temp._0741_Cherry_Pickup _0741_Cherry_Pickup = null;
}
