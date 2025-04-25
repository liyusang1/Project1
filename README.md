# 🎁 (JDBC 기반 콘솔 프로젝트)

> 순수 JDBC만을 사용하여 설계한 간단한 시스템입니다.  
> 기본적인 MVC 아키텍처를 따르며, 코드의 유지보수성과 확장성을 고려하여 패키지를 구조화했습니다.

---

## 🧱 프로젝트 구조
📦 your.project.root
│
├── 📁 controller
│   └── SampleGiftController.java
│
├── 📁 service
│   └── SampleGiftService.java
│
├── 📁 dao
│   └── SampleGiftDao.java
│
├── 📁 model
│   └── SampleGift.java
│
├── 📁 view
│   └── Main.java
│
├── 📁 util
│   └── DBUtil.java 


## 🧱 Flow
[Main.java] (view)
│
▼
[SampleGiftController.java] (controller)
│
▼
[SampleGiftService.java] (service)
│
▼
[SampleGiftDao.java] (dao)
│
▼
Database
