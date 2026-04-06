

error in line 52892:きゃっ, きゃあ(кя, кя:)〔006-72-70〕
Exception in thread "main" java.lang.StringIndexOutOfBoundsException: String index out of range: 0
	at java.lang.String.charAt(String.java:658)
	at ru.tetsu.dao.misc.utils.RomanizationUtils.romanizeKanaSyllable(RomanizationUtils.java:69)
	at ru.tetsu.dao.misc.utils.RomanizationUtils.romanizeKanaText(RomanizationUtils.java:116)
	at ru.tetsu.dataload.WarodaiReadUtils.parseKeyWordLine(WarodaiReadUtils.java:57)
	at ru.tetsu.dataload.WarodaiReadUtils.parseWord(WarodaiReadUtils.java:91)
	at ru.tetsu.dataload.ReadWarodaiDictionary.parseAndSaveWord(ReadWarodaiDictionary.java:57)
	at ru.tetsu.dataload.ReadWarodaiDictionary.readDictionary(ReadWarodaiDictionary.java:160)
	at ru.tetsu.dataload.ReadWarodaiDictionary.main(ReadWarodaiDictionary.java:173)

	
	