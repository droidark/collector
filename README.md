# COMICORP - COLLECTOR
## We're making our own whakoom with blackjack and hookers

### Before to run
To run the application locally, it's necessary to run a Docker container with Redis to use the functionality for blacklist tokens

### Commands to generate a valid public and private keys
Generate private key
```shell
  openssl genrsa -out access-token-private.pem 4096
```

Generate public key
```shell
  openssl rsa -in access-token-private.pem -pubout -out access-token-public.pem
```

