# Sonda Espacial REST API

Implementado com o [Spring Boot Framework](http://spring.io/projects/spring-boot).

Você pode acessar a documentação da API e até mesmo testá-la usando o seguinte link:  'https://intense-journey-80716.herokuapp.com/swagger-ui.html'.

## Endpoints

### Obter a posição atual da sonda : `GET /posicao`
### Mover a sonda : `PUT /posicao`

Formato do json de requisição
```
{
  movimentos: ['GE', 'M', 'M', 'M', 'GD', 'M', 'M']
}
```
Movimentos válidos:
```
M => Prosseguir
GE => Girar para a esquerda
GD => Girar para a direita
GB => Girar para baixo
GC => Girar para cima
```
### Colocar a sonda na posição inicial : `DELETE /posicao`
