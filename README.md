# Automation Testing Project — OrangeHRM (Selenium + TestNG + POM)

مشروع أتمتة اختبار لموقع OrangeHRM Demo باستخدام Selenium WebDriver, TestNG, و Page Object Model.

## المتطلبات قبل التشغيل (Prerequisites)
1. **Java JDK 17** أو أحدث مثبت على جهازك.
2. **Apache Maven** مثبت (`mvn -version` للتأكد).
3. **Google Chrome** مثبت (المشروع يستخدم WebDriverManager فينزل الـ driver تلقائي، مش محتاج تنزله يدوي).
4. **Allure Commandline** لعرض التقرير (اختياري لو عايز `allure open`):
   - على Windows: `scoop install allure`
   - على Mac: `brew install allure`

## هيكل المشروع
```
automation-testing-project/
├── pom.xml                        # ملف Maven (الاعتماديات + الإعدادات)
├── testng.xml                     # ملف تشغيل الاختبارات (Parallel execution)
├── src/test/java/
│   ├── base/
│   │   ├── DriverFactory.java     # ThreadLocal<WebDriver> للتشغيل المتوازي
│   │   └── BaseTest.java          # Setup/Teardown لكل تست
│   ├── pages/                     # Page Object Model - كل صفحة كلاس مستقل
│   ├── utils/
│   │   ├── ConfigReader.java      # يقرأ config.properties
│   │   ├── JsonDataProvider.java  # يقرأ testdata.json (Data-Driven)
│   │   └── RetryAnalyzer.java     # إعادة محاولة التست الفاشل (Bonus)
│   ├── listeners/
│   │   └── RetryListener.java     # يربط RetryAnalyzer بكل التستات تلقائي
│   └── tests/                     # 11 Test Case مقسمة على 3 كلاسات
└── src/test/resources/
    ├── config.properties          # base.url / browser / explicit.wait
    ├── testdata.json              # بيانات الاختبار (Data-Driven)
    └── log4j2.xml                 # إعدادات اللوج
```

## طريقة التشغيل

### 1) تشغيل كل الاختبارات
```bash
mvn clean test
```
هيشغل الـ testng.xml تلقائي (معرف في الـ pom.xml)، وهيفتح 3 متصفحات كروم بالتوازي (test classes بالتوازي حسب `parallel="tests"`).

### 2) توليد تقرير Allure
```bash
allure generate allure-results --clean -o allure-report
allure open allure-report
```

### 3) مراجعة اللوج
كل خطوة بتتسجل في: `logs/automation.log`

## ملاحظات مهمة قبل ما تشغل المشروع فعليًا

1. **بيانات الموظف الموجود مسبقًا (TC4)**: في `testdata.json` حطيت اسم تجريبي
   `"Peter Mac"` كموظف "موجود بالفعل" في PIM. **لازم تتأكد بنفسك** إن الاسم ده
   فعلاً موجود في نسخة الـ Demo وقت التشغيل (بيانات الـ OrangeHRM Demo بترجع
   لوضعها الأصلي كل فترة)، أو غيّره لاسم موظف تشوفه فعليًا في قائمة الموظفين.

2. **الـ Locators (XPath/CSS)**: كتبتها بناءً على الـ HTML structure المعروف
   لموقع OrangeHRM demo (نسخة oxd- components). لو الموقع اتحدّث بعد كتابة
   الكود، ممكن تحتاج تتأكد من الـ locators بفتح Developer Tools (F12) وتقارنها.

3. **الأسماء `firstNameRequiredError` في `AddEmployeePage`**: الـ XPath محتاج
   مراجعة على الصفحة الفعلية لو الهيكل اختلف، لأني مبنيتوش على تشغيل حي
   للموقع (البيئة اللي بشتغل فيها معزولة عن الإنترنت العام).

4. **لسه محتاج تعمل بنفسك**:
   - `git init` ورفع المشروع على GitHub باسم `automation-testing-project`
   - التأكد إن الريبو Public
   - تشغيل `mvn test` فعليًا على جهازك عشان تتأكد كل الـ locators شغالة
   - بعد التأكد من نجاح التستات، تولّد الـ Allure report وترفعه كمان في الريبو
     (بدون `allure-results` كما هو مطلوب في التعليمات)

## البريد الإلكتروني للتسليم
Subject: `GP Task [اسمك الكامل] – G2`
Body: رابط الريبو فقط
