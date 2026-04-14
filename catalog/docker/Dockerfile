FROM pgvector/pgvector:pg17

ENV POSTGRES_DB=catalog
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

RUN mkdir -p /docker-entrypoint-initdb.d
COPY init.sql /docker-entrypoint-initdb.d/init.sql