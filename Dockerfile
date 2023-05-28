FROM postgres

ENV POSTGRES_PASSWORD=Student_1234

# Create directories and files
RUN mkdir -p /usr/local/share/postgresql
RUN touch /usr/local/share/postgresql/pg_hba.conf

# Allow connections from outside the container
RUN echo "host all all 127.0.0.1/32 md5" >> /usr/local/share/postgresql/pg_hba.conf
RUN echo "host all all ::1/128 md5" >> /usr/local/share/postgresql/pg_hba.conf
RUN echo "listen_addresses='*'" >> /usr/local/share/postgresql/postgresql.conf

# Create a file to set up the schema and user
RUN mkdir -p /docker-entrypoint-initdb.d
RUN touch /docker-entrypoint-initdb.d/init.sql

# grant full access to 'multitenancy' database to user 'spring'
RUN echo "CREATE USER spring_proj WITH PASSWORD 'spring_proj';" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "CREATE DATABASE spring_proj OWNER spring_proj;" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "GRANT ALL PRIVILEGES ON DATABASE spring_proj TO spring_proj;" >> /docker-entrypoint-initdb.d/init.sql
RUN echo "GRANT ALL PRIVILEGES ON DATABASE spring_proj TO postgres;" >> /docker-entrypoint-initdb.d/init.sql

EXPOSE 5432
