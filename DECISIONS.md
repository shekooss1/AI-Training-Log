### 16/8/2026 : day 1 tasks are done by setup all programs 

1. # Tech stack 
     backend : springboot + java 
     database : postgresSQL 
      # This over other database because it is free and relational DB fits my app well 
     ML service : FastAPI
      # used python for ML because it better and fits the ecosystem

2. # Database schema management
   ddl-auto: update (temporary)
   # Using Hibernate auto-update for now while schema is still changing daily.
   # Will switch to Flyway migrations before deploying to production.

3. # postgres run on docker
     Easier to rebuild , matches later phases 

