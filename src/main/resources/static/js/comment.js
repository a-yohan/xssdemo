(function () {
  $(document).ready(function () {
    var url = `${document.location.origin}${document.location.pathname}/comment`;
    var page = 0
    var rendered = []
    var state = $(document.body).data()
    var commentBox = $('#commentBox')
    var loadButton = $(`<button class="button is-info is-small is-fullwidth">Load Comment</button>`)
    var input = $(`<textarea class="textarea" rows="2"></textarea>`)
    var submitButton = $(`<button class="button is-link is-small is-fullwidth mt-1">Submit Comment</button>`)
    var list = $('<div></div>')

    var loading = false
    var sumbiting = false
    var headers = {}
    var csrfField = $("meta[name='_csrf_header']").attr("content")
    var csrfToken = $("meta[name='_csrf']").attr("content")
    headers[csrfField] = csrfToken

    function loadComment() {
      loading = true
      loadButton.addClass('is-loading')
      $.get(url, { p: page })
        .done(function (res) {
          res.content.forEach(renderComment)
          if (!res.last) {
            page++
          }
        })
        .always(function () {
          loadButton.removeClass('is-loading')
          loading = false
        })
    }
    function renderComment(comment) {
      if (rendered.includes(comment.id)) return
      var createdAt = (new Date(comment.createdAt))
      var dt = createdAt.toLocaleDateString() + " " + createdAt.toLocaleTimeString().replaceAll(".", ":")
      var card = $(`
      <div class="card p-2 mt-2">    
        <div class="content">
          <div class="is-size-6 is-uppercase has-text-weight-medium username">${comment.user.name}</div>
          <p>${comment.content}</p>
          <span class="is-size-7">${dt}</span>
        </div>
      </div>
      `)
      list.append(card)
      rendered.push(comment.id)
    }
    function submitComment() {
      if (sumbiting) return
      sumbiting = true
      submitButton.addClass('is-loading')
      $.post({
        url,
        data: JSON.stringify({ content: input.val() }),
        contentType: 'application/json',
        headers,
        xhrFields: {
          withCredentials: true
        }
      }).done(function (res) {
        renderComment(res)
      }).always(function () {
        submitButton.removeClass('is-loading')
        sumbiting = false
      })
    }

    if (state.loggedin) {
      commentBox.append(input)
      commentBox.append(submitButton)
      commentBox.append(`<hr />`)
      submitButton.click(submitComment)
    }
    commentBox.append(list)
    commentBox.append(loadButton)
    loadButton.click(loadComment)
  })
})()