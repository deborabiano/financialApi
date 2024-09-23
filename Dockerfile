FROM java:8u91
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac Main.java
CMD ["java", "Main"]

ENTRYPOINT ["top", "-b"]
