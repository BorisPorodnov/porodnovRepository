### How-to use

1. Open project dir in terminal.
2. Execute `docker-compose up -d`
3. Open http://localhost:5433 Login: `test@postgres` password: `postgres`
4. Create new server:
    - name: `my-db`
    - host: `host.docker.internal`
    - port: 5432
    - database: `postgres`
    - username: `postgres`
    - password: `postgres`
5. Click Save button
  
Open api.md do request, and look images folder