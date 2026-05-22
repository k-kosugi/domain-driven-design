import { useMemo, useState } from 'react'
import './App.css'

const DEFAULT_API_BASE_URL = import.meta.env.VITE_CATALOG_API_BASE_URL?.replace(/\/$/, '') ?? '/api'

const createAuthor = () => ({
  id: '',
  firstName: '',
  middleName: '',
  lastName: '',
})

const initialForm = {
  isbn13: '',
  title: '',
  contents: '',
  authors: [createAuthor()],
  publisher: {
    id: '',
    name: '',
  },
}

function App() {
  const [form, setForm] = useState(initialForm)
  const [status, setStatus] = useState({ type: 'idle', message: '' })
  const [isSubmitting, setIsSubmitting] = useState(false)

  const payloadPreview = useMemo(
    () =>
      JSON.stringify(
        {
          isbn13: form.isbn13.trim(),
          title: form.title.trim(),
          contents: form.contents.trim(),
          authors: form.authors.map((author) => ({
            id: author.id.trim(),
            firstName: author.firstName.trim(),
            middleName: author.middleName.trim(),
            lastName: author.lastName.trim(),
          })),
          publisher: {
            id: form.publisher.id.trim(),
            name: form.publisher.name.trim(),
          },
        },
        null,
        2,
      ),
    [form],
  )

  const handleChange = (event) => {
    const { name, value } = event.target

    setForm((current) => ({
      ...current,
      [name]: value,
    }))
  }

  const handlePublisherChange = (event) => {
    const { name, value } = event.target

    setForm((current) => ({
      ...current,
      publisher: {
        ...current.publisher,
        [name]: value,
      },
    }))
  }

  const handleAuthorChange = (index, event) => {
    const { name, value } = event.target

    setForm((current) => ({
      ...current,
      authors: current.authors.map((author, authorIndex) =>
        authorIndex === index
          ? {
              ...author,
              [name]: value,
            }
          : author,
      ),
    }))
  }

  const addAuthor = () => {
    setForm((current) => ({
      ...current,
      authors: [...current.authors, createAuthor()],
    }))
  }

  const removeAuthor = (index) => {
    setForm((current) => ({
      ...current,
      authors:
        current.authors.length === 1
          ? current.authors
          : current.authors.filter((_, authorIndex) => authorIndex !== index),
    }))
  }

  const resetForm = () => {
    setForm(initialForm)
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    const payload = {
      isbn13: form.isbn13.trim(),
      title: form.title.trim(),
      contents: form.contents.trim(),
      authors: form.authors.map((author) => ({
        id: author.id.trim(),
        firstName: author.firstName.trim(),
        middleName: author.middleName.trim() || null,
        lastName: author.lastName.trim(),
      })),
      publisher: {
        id: form.publisher.id.trim(),
        name: form.publisher.name.trim(),
      },
    }

    const hasIncompleteAuthor = payload.authors.some(
      (author) => !author.id || !author.firstName || !author.lastName,
    )

    if (hasIncompleteAuthor) {
      setStatus({
        type: 'error',
        message: '著者の ID、名、姓はすべて入力してください。',
      })
      return
    }

    setIsSubmitting(true)
    setStatus({
      type: 'idle',
      message: '',
    })

    try {
      const response = await fetch(`${DEFAULT_API_BASE_URL}/books`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      })

      if (!response.ok) {
        const errorMessage = await readErrorMessage(response)

        throw new Error(errorMessage)
      }

      setStatus({
        type: 'success',
        message: '書籍を登録しました。',
      })
      resetForm()
    } catch (error) {
      setStatus({
        type: 'error',
        message:
          error instanceof Error
            ? error.message
            : '書籍の登録に失敗しました。API の接続先を確認してください。',
      })
    } finally {
      setIsSubmitting(false)
    }
  }

  return (
    <main className="page-shell">
      <section className="hero-panel">
        <div className="hero-copy">
          <span className="eyebrow">Catalog authoring studio</span>
          <h1>Catalog API に接続する BookDTO 登録 UI</h1>
          <p className="hero-description">
            catalog の BookRestAPIController に対して、BookDTO と関連する AuthorDTO、
            PublisherDTO を一度に登録する React ベースの画面です。
          </p>
          <dl className="hero-meta">
            <div>
              <dt>API endpoint</dt>
              <dd>POST {DEFAULT_API_BASE_URL}/books</dd>
            </div>
            <div>
              <dt>Authors</dt>
              <dd>One book to many authors</dd>
            </div>
            <div>
              <dt>Publisher</dt>
              <dd>One book to one publisher</dd>
            </div>
          </dl>
        </div>

        <aside className="preview-card" aria-label="送信プレビュー">
          <p className="preview-label">Request preview</p>
          <pre>{payloadPreview}</pre>
        </aside>
      </section>

      <section className="form-panel">
        <div className="panel-header">
          <div>
            <span className="section-tag">Registration form</span>
            <h2>BookDTO を作成</h2>
          </div>
          <button
            type="button"
            className="secondary-button"
            onClick={resetForm}
            disabled={isSubmitting}
          >
            入力をリセット
          </button>
        </div>

        <form className="catalog-form" onSubmit={handleSubmit}>
          <section className="form-section">
            <div className="section-heading">
              <h3>Book</h3>
              <p>書籍の識別子と本文を入力します。</p>
            </div>

            <div className="field-grid">
              <label>
                <span>ISBN13</span>
                <input
                  name="isbn13"
                  value={form.isbn13}
                  onChange={handleChange}
                  placeholder="978-4-87311-565-8"
                  required
                />
              </label>
              <label>
                <span>Title</span>
                <input
                  name="title"
                  value={form.title}
                  onChange={handleChange}
                  placeholder="Domain-Driven Design"
                  required
                />
              </label>
            </div>

            <label className="textarea-field">
              <span>Contents</span>
              <textarea
                name="contents"
                value={form.contents}
                onChange={handleChange}
                placeholder="書籍の内容や要約を入力してください。"
                rows="8"
                required
              />
            </label>
          </section>

          <section className="form-section">
            <div className="section-heading section-heading-inline">
              <div>
                <h3>Authors</h3>
                <p>BookDTO に紐づく AuthorDTO を複数行で登録できます。</p>
              </div>
              <button
                type="button"
                className="secondary-button"
                onClick={addAuthor}
                disabled={isSubmitting}
              >
                著者を追加
              </button>
            </div>

            <div className="author-list">
              {form.authors.map((author, index) => (
                <article className="author-card" key={`author-${index}`}>
                  <div className="author-card-header">
                    <div>
                      <span className="card-index">Author {index + 1}</span>
                      <p>著者 ID と氏名を入力します。</p>
                    </div>
                    <button
                      type="button"
                      className="text-button"
                      onClick={() => removeAuthor(index)}
                      disabled={form.authors.length === 1 || isSubmitting}
                    >
                      削除
                    </button>
                  </div>

                  <div className="field-grid field-grid-authors">
                    <label>
                      <span>Author ID</span>
                      <input
                        name="id"
                        value={author.id}
                        onChange={(event) => handleAuthorChange(index, event)}
                        placeholder="author-001"
                        required
                      />
                    </label>
                    <label>
                      <span>First name</span>
                      <input
                        name="firstName"
                        value={author.firstName}
                        onChange={(event) => handleAuthorChange(index, event)}
                        placeholder="Eric"
                        required
                      />
                    </label>
                    <label>
                      <span>Middle name</span>
                      <input
                        name="middleName"
                        value={author.middleName}
                        onChange={(event) => handleAuthorChange(index, event)}
                        placeholder="J."
                      />
                    </label>
                    <label>
                      <span>Last name</span>
                      <input
                        name="lastName"
                        value={author.lastName}
                        onChange={(event) => handleAuthorChange(index, event)}
                        placeholder="Evans"
                        required
                      />
                    </label>
                  </div>
                </article>
              ))}
            </div>
          </section>

          <section className="form-section">
            <div className="section-heading">
              <h3>Publisher</h3>
              <p>BookDTO に紐づく PublisherDTO を入力します。</p>
            </div>

            <div className="field-grid">
              <label>
                <span>Publisher ID</span>
                <input
                  name="id"
                  value={form.publisher.id}
                  onChange={handlePublisherChange}
                  placeholder="publisher-001"
                  required
                />
              </label>
              <label>
                <span>Publisher name</span>
                <input
                  name="name"
                  value={form.publisher.name}
                  onChange={handlePublisherChange}
                  placeholder="Addison-Wesley"
                  required
                />
              </label>
            </div>
          </section>

          <footer className="form-footer">
            <div className={`status-message ${status.type}`}>
              {status.message || 'BookDTO、AuthorDTO、PublisherDTO の関係を保ったまま送信します。'}
            </div>
            <button type="submit" className="primary-button" disabled={isSubmitting}>
              {isSubmitting ? '登録中...' : 'BookDTO を登録'}
            </button>
          </footer>
        </form>
      </section>
    </main>
  )
}

async function readErrorMessage(response) {
  const contentType = response.headers.get('content-type') ?? ''

  if (contentType.includes('application/json')) {
    const payload = await response.json()

    if (typeof payload?.message === 'string' && payload.message.trim()) {
      return payload.message
    }

    return JSON.stringify(payload)
  }

  const text = await response.text()
  return text || '書籍の登録に失敗しました。API のレスポンスを確認してください。'
}

export default App
