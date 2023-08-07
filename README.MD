
# Project summary

REST API using Spring Data R2DBC.

# Script

```bash
function reactivePostAsync() { 
  local URL="http://localhost:8090/async/books"
  POST_BODY=$(cat << EOF
  {
    "title": "Book title 1",
    "description": "Book description 1",
    "published": true
  }
EOF
)

  local RESPONSE=$(curl -sk "$URL" -X POST -H "Content-Type: application/json" -d "$POST_BODY")

  echo "$RESPONSE" | jq empty
  exit_status=$?

  echo "\n=$URL=\n";
  if [ $exit_status -eq 0 ]; then
    echo "$RESPONSE" | python3 -m json.tool;
  else 
    echo "***Operation failed***"
    curl -v "$URL"
  fi

  echo "\n==\n";
}
```
