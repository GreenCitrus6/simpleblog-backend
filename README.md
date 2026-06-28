solution to the Blogging Platform API project on roadmap.sh: [Link](https://roadmap.sh/projects/blogging-platform-api)

## api endpoints

* GET /api/v1/blog

optional 'term' param

fetches all blog posts, or all posts mentioning a specified term in their title, content, category or tags
  
* GET /api/v1/blog/{id}

get a specific post by id

 
* DELETE /delete/{id}

delete a post by id
 
* POST /add

create a new post
 
* PUT /update/{id}

update an existing post