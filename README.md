# AAA + Feed APIs

# Feed - Category 
 - 다:다 관계 
 - OneToMany -> FeedCategory <- OneToMany  중간엔터티를 만들어서 N:M 
 


# Schedule


![image](https://user-images.githubusercontent.com/60733417/178443894-640ddf90-3f4e-4b88-a61d-db0f113eaef2.png)

User - Schduler - Scheduler 

### N:M 을 1:N 으로 풀기위한 몸부림

1. User는 여러개 Scheduler 를 등록 할 수 있다.(1:N). user - scheduler (1:N)
2. Scheduler는 여러개 Schedule 을 가지고 있다. (1:N)
3. User는 다른 User의 Scheduler를 follow 할 수 있다. user - followSchdule (1:N)
4. Scheduler 는 category를 가진다. scheduler - schedulerCategory (1:N)

