--
-- PostgreSQL database dump
--

-- Dumped from database version 17.6
-- Dumped by pg_dump version 17.0

-- Started on 2025-10-10 10:14:39

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16563)
-- Name: addresses; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.addresses (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    is_default boolean,
    phone_number character varying(20) NOT NULL,
    province character varying(100) NOT NULL,
    province_code character varying(10),
    recipient_name character varying(100) NOT NULL,
    street_address text NOT NULL,
    updated_at timestamp(6) without time zone,
    ward character varying(100) NOT NULL,
    ward_code character varying(10),
    user_id bigint NOT NULL
);


ALTER TABLE public.addresses OWNER TO utephonehub_user;

--
-- TOC entry 217 (class 1259 OID 16562)
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.addresses_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4109 (class 0 OID 0)
-- Dependencies: 217
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.addresses_id_seq OWNED BY public.addresses.id;


--
-- TOC entry 220 (class 1259 OID 16572)
-- Name: brands; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.brands (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    description text,
    logo_url character varying(500),
    name character varying(100) NOT NULL,
    updated_at timestamp(6) without time zone
);


ALTER TABLE public.brands OWNER TO utephonehub_user;

--
-- TOC entry 219 (class 1259 OID 16571)
-- Name: brands_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.brands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.brands_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4110 (class 0 OID 0)
-- Dependencies: 219
-- Name: brands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.brands_id_seq OWNED BY public.brands.id;


--
-- TOC entry 222 (class 1259 OID 16581)
-- Name: cart_items; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.cart_items (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    quantity integer NOT NULL,
    updated_at timestamp(6) without time zone,
    cart_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.cart_items OWNER TO utephonehub_user;

--
-- TOC entry 221 (class 1259 OID 16580)
-- Name: cart_items_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.cart_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cart_items_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4111 (class 0 OID 0)
-- Dependencies: 221
-- Name: cart_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.cart_items_id_seq OWNED BY public.cart_items.id;


--
-- TOC entry 224 (class 1259 OID 16588)
-- Name: carts; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.carts (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    user_id bigint NOT NULL
);


ALTER TABLE public.carts OWNER TO utephonehub_user;

--
-- TOC entry 223 (class 1259 OID 16587)
-- Name: carts_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.carts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.carts_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4112 (class 0 OID 0)
-- Dependencies: 223
-- Name: carts_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.carts_id_seq OWNED BY public.carts.id;


--
-- TOC entry 226 (class 1259 OID 16595)
-- Name: categories; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.categories (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    description text,
    name character varying(100) NOT NULL,
    updated_at timestamp(6) without time zone,
    parent_id bigint
);


ALTER TABLE public.categories OWNER TO utephonehub_user;

--
-- TOC entry 225 (class 1259 OID 16594)
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.categories_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4113 (class 0 OID 0)
-- Dependencies: 225
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;


--
-- TOC entry 228 (class 1259 OID 16604)
-- Name: order_items; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.order_items (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    price numeric(12,2) NOT NULL,
    quantity integer NOT NULL,
    order_id bigint NOT NULL,
    product_id bigint NOT NULL
);


ALTER TABLE public.order_items OWNER TO utephonehub_user;

--
-- TOC entry 227 (class 1259 OID 16603)
-- Name: order_items_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.order_items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.order_items_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4114 (class 0 OID 0)
-- Dependencies: 227
-- Name: order_items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.order_items_id_seq OWNED BY public.order_items.id;


--
-- TOC entry 230 (class 1259 OID 16611)
-- Name: orders; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.orders (
    id bigint NOT NULL,
    city character varying(100) NOT NULL,
    created_at timestamp(6) without time zone,
    email character varying(100) NOT NULL,
    order_code character varying(50) NOT NULL,
    payment_method character varying(255) NOT NULL,
    phone_number character varying(20) NOT NULL,
    recipient_name character varying(100) NOT NULL,
    status character varying(255) NOT NULL,
    street_address text NOT NULL,
    total_amount numeric(12,2) NOT NULL,
    updated_at timestamp(6) without time zone,
    user_id bigint,
    voucher_id bigint,
    CONSTRAINT orders_payment_method_check CHECK (((payment_method)::text = ANY ((ARRAY['COD'::character varying, 'BANK_TRANSFER'::character varying, 'CREDIT_CARD'::character varying])::text[]))),
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY ((ARRAY['PENDING'::character varying, 'PROCESSING'::character varying, 'SHIPPED'::character varying, 'DELIVERED'::character varying, 'CANCELLED'::character varying])::text[])))
);


ALTER TABLE public.orders OWNER TO utephonehub_user;

--
-- TOC entry 229 (class 1259 OID 16610)
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4115 (class 0 OID 0)
-- Dependencies: 229
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- TOC entry 232 (class 1259 OID 16622)
-- Name: password_reset_tokens; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.password_reset_tokens (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    expiry_date timestamp(6) without time zone NOT NULL,
    token character varying(255) NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.password_reset_tokens OWNER TO utephonehub_user;

--
-- TOC entry 231 (class 1259 OID 16621)
-- Name: password_reset_tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.password_reset_tokens_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.password_reset_tokens_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4116 (class 0 OID 0)
-- Dependencies: 231
-- Name: password_reset_tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.password_reset_tokens_id_seq OWNED BY public.password_reset_tokens.id;


--
-- TOC entry 234 (class 1259 OID 16629)
-- Name: product_images; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.product_images (
    id bigint NOT NULL,
    alt_text character varying(200),
    created_at timestamp(6) without time zone,
    image_url character varying(500) NOT NULL,
    is_primary boolean,
    product_id bigint NOT NULL
);


ALTER TABLE public.product_images OWNER TO utephonehub_user;

--
-- TOC entry 233 (class 1259 OID 16628)
-- Name: product_images_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.product_images_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.product_images_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4117 (class 0 OID 0)
-- Dependencies: 233
-- Name: product_images_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.product_images_id_seq OWNED BY public.product_images.id;


--
-- TOC entry 236 (class 1259 OID 16638)
-- Name: products; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.products (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    description text,
    name character varying(200) NOT NULL,
    price numeric(12,2) NOT NULL,
    specifications jsonb,
    status boolean NOT NULL,
    stock_quantity integer NOT NULL,
    thumbnail_url character varying(500),
    updated_at timestamp(6) without time zone,
    brand_id bigint NOT NULL,
    category_id bigint NOT NULL
);


ALTER TABLE public.products OWNER TO utephonehub_user;

--
-- TOC entry 235 (class 1259 OID 16637)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4118 (class 0 OID 0)
-- Dependencies: 235
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- TOC entry 238 (class 1259 OID 16647)
-- Name: review_likes; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.review_likes (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    review_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.review_likes OWNER TO utephonehub_user;

--
-- TOC entry 237 (class 1259 OID 16646)
-- Name: review_likes_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.review_likes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.review_likes_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4119 (class 0 OID 0)
-- Dependencies: 237
-- Name: review_likes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.review_likes_id_seq OWNED BY public.review_likes.id;


--
-- TOC entry 240 (class 1259 OID 16654)
-- Name: reviews; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.reviews (
    id bigint NOT NULL,
    comment text,
    created_at timestamp(6) without time zone,
    rating integer NOT NULL,
    updated_at timestamp(6) without time zone,
    product_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT reviews_rating_check CHECK (((rating >= 1) AND (rating <= 5)))
);


ALTER TABLE public.reviews OWNER TO utephonehub_user;

--
-- TOC entry 239 (class 1259 OID 16653)
-- Name: reviews_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.reviews_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.reviews_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4120 (class 0 OID 0)
-- Dependencies: 239
-- Name: reviews_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.reviews_id_seq OWNED BY public.reviews.id;


--
-- TOC entry 242 (class 1259 OID 16664)
-- Name: users; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    created_at timestamp(6) without time zone,
    email character varying(100) NOT NULL,
    full_name character varying(100) NOT NULL,
    password_hash character varying(255) NOT NULL,
    phone_number character varying(20),
    role character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone,
    username character varying(50) NOT NULL,
    CONSTRAINT users_role_check CHECK (((role)::text = ANY ((ARRAY['customer'::character varying, 'admin'::character varying])::text[]))),
    CONSTRAINT users_status_check CHECK (((status)::text = ANY ((ARRAY['active'::character varying, 'locked'::character varying, 'pending'::character varying])::text[])))
);


ALTER TABLE public.users OWNER TO utephonehub_user;

--
-- TOC entry 241 (class 1259 OID 16663)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4121 (class 0 OID 0)
-- Dependencies: 241
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 244 (class 1259 OID 16675)
-- Name: vouchers; Type: TABLE; Schema: public; Owner: utephonehub_user
--

CREATE TABLE public.vouchers (
    id bigint NOT NULL,
    code character varying(50) NOT NULL,
    created_at timestamp(6) without time zone,
    discount_type character varying(255) NOT NULL,
    discount_value numeric(12,2) NOT NULL,
    expiry_date timestamp(6) without time zone,
    max_usage integer,
    min_order_value numeric(12,2),
    status character varying(255) NOT NULL,
    updated_at timestamp(6) without time zone,
    CONSTRAINT vouchers_discount_type_check CHECK (((discount_type)::text = ANY ((ARRAY['PERCENTAGE'::character varying, 'FIXED_AMOUNT'::character varying])::text[]))),
    CONSTRAINT vouchers_status_check CHECK (((status)::text = ANY ((ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying, 'EXPIRED'::character varying])::text[])))
);


ALTER TABLE public.vouchers OWNER TO utephonehub_user;

--
-- TOC entry 243 (class 1259 OID 16674)
-- Name: vouchers_id_seq; Type: SEQUENCE; Schema: public; Owner: utephonehub_user
--

CREATE SEQUENCE public.vouchers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vouchers_id_seq OWNER TO utephonehub_user;

--
-- TOC entry 4122 (class 0 OID 0)
-- Dependencies: 243
-- Name: vouchers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: utephonehub_user
--

ALTER SEQUENCE public.vouchers_id_seq OWNED BY public.vouchers.id;


--
-- TOC entry 3852 (class 2604 OID 16566)
-- Name: addresses id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.addresses ALTER COLUMN id SET DEFAULT nextval('public.addresses_id_seq'::regclass);


--
-- TOC entry 3853 (class 2604 OID 16575)
-- Name: brands id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.brands ALTER COLUMN id SET DEFAULT nextval('public.brands_id_seq'::regclass);


--
-- TOC entry 3854 (class 2604 OID 16584)
-- Name: cart_items id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.cart_items ALTER COLUMN id SET DEFAULT nextval('public.cart_items_id_seq'::regclass);


--
-- TOC entry 3855 (class 2604 OID 16591)
-- Name: carts id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.carts ALTER COLUMN id SET DEFAULT nextval('public.carts_id_seq'::regclass);


--
-- TOC entry 3856 (class 2604 OID 16598)
-- Name: categories id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);


--
-- TOC entry 3857 (class 2604 OID 16607)
-- Name: order_items id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.order_items ALTER COLUMN id SET DEFAULT nextval('public.order_items_id_seq'::regclass);


--
-- TOC entry 3858 (class 2604 OID 16614)
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- TOC entry 3859 (class 2604 OID 16625)
-- Name: password_reset_tokens id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.password_reset_tokens ALTER COLUMN id SET DEFAULT nextval('public.password_reset_tokens_id_seq'::regclass);


--
-- TOC entry 3860 (class 2604 OID 16632)
-- Name: product_images id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.product_images ALTER COLUMN id SET DEFAULT nextval('public.product_images_id_seq'::regclass);


--
-- TOC entry 3861 (class 2604 OID 16641)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- TOC entry 3862 (class 2604 OID 16650)
-- Name: review_likes id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.review_likes ALTER COLUMN id SET DEFAULT nextval('public.review_likes_id_seq'::regclass);


--
-- TOC entry 3863 (class 2604 OID 16657)
-- Name: reviews id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.reviews ALTER COLUMN id SET DEFAULT nextval('public.reviews_id_seq'::regclass);


--
-- TOC entry 3864 (class 2604 OID 16667)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3865 (class 2604 OID 16678)
-- Name: vouchers id; Type: DEFAULT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.vouchers ALTER COLUMN id SET DEFAULT nextval('public.vouchers_id_seq'::regclass);


--
-- TOC entry 4076 (class 0 OID 16563)
-- Dependencies: 218
-- Data for Name: addresses; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.addresses (id, created_at, is_default, phone_number, province, province_code, recipient_name, street_address, updated_at, ward, ward_code, user_id) FROM stdin;
5	2025-10-08 22:21:49.083889	t	1	Cao Bằng	4	ute 3	42 đường số 19	2025-10-08 22:21:49.083911	Xã Huy Giáp	1354	3
3	2025-10-08 22:03:44.895177	f	0822659469	Thái Nguyên	19	Luu Thao Phuong	hcm	2025-10-08 22:03:44.895207	Phường Bắc Kạn	1843	2
4	2025-10-08 22:04:15.429398	t	0399963463	Thành phố Hà Nội	1	Thao Phuong	20 duong so 19 HCM	2025-10-09 19:01:52.085491	Phường Ba Đình	4	2
14	2025-10-09 15:59:04.689949	t	0123456789	Ho Chi Minh	79	Nguyen Van Test	123 Test Street	2025-10-09 15:59:04.689966	Phuong Ben Nghe	27259	13
16	2025-10-10 00:16:18.868853	f	0132321312	Điện Biên	11	kien hung	100 abc	2025-10-10 00:16:18.868864	Phường Điện Biên Phủ	3127	12
15	2025-10-10 00:16:02.122715	t	0132321312	Thành phố Hà Nội	1	kien hung	100 abc	2025-10-10 00:16:26.48314	Phường Ngọc Hà	8	12
7	2025-10-09 00:42:34.94026	f	0399963463	Cao Bằng	4	ute 3	42 đường số 19	2025-10-09 00:42:34.940272	Phường Thục Phán	1273	4
6	2025-10-09 00:42:10.1143	t	0912345678	Thành phố Hồ Chí Minh	79	ad	456 Nguyễn Huệ	2025-10-09 16:32:27.185072	Phường Tân Định	26737	4
1	2025-10-08 21:50:56.032162	t	03999634631	Đắk Lắk	66	Luu Th	2 đường số 1	2025-10-09 16:37:09.924705	Xã Krông Năng	24343	1
2	2025-10-08 21:51:19.94153	f	0822659469	Thành phố Hà Nội	1	Thao Ph 2	192 phố hàng Bài	2025-10-09 16:37:14.473615	Phường Hoàn Kiếm	70	1
\.


--
-- TOC entry 4078 (class 0 OID 16572)
-- Dependencies: 220
-- Data for Name: brands; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.brands (id, created_at, description, logo_url, name, updated_at) FROM stdin;
1	\N	Laptop và thiết bị điện tử Asus	\N	Asus	\N
2	\N	Tai nghe và thiết bị âm thanh Sony	\N	Sony	\N
3	\N	Thương hiệu công nghệ Trung Quốc với Mi, Redmi	https://upload.wikimedia.org/wikipedia/commons/2/29/Xiaomi_logo.svg	Xiaomi	\N
4	\N	Thương hiệu điện thoại với công nghệ camera tiên tiến	https://upload.wikimedia.org/wikipedia/commons/c/c0/OPPO_Logo.svg	OPPO	\N
5	\N	Thương hiệu điện thoại với thiết kế sang trọng	https://upload.wikimedia.org/wikipedia/commons/e/e7/Vivo_logo.svg	Vivo	\N
6	\N	Thương hiệu điện thoại dành cho giới trẻ	https://upload.wikimedia.org/wikipedia/commons/9/91/Realme_logo.svg	Realme	\N
7	\N	Flagship killer với hiệu năng cao	https://upload.wikimedia.org/wikipedia/commons/0/09/OnePlus_logo.svg	OnePlus	\N
8	\N	Thương hiệu công nghệ Trung Quốc	https://upload.wikimedia.org/wikipedia/commons/0/04/Huawei_Standard_logo.svg	Huawei	\N
9	\N	Thương hiệu điện thoại kinh điển	https://upload.wikimedia.org/wikipedia/commons/0/08/Nokia_logo_2023.svg	Nokia	\N
10	\N	Điện thoại Xperia cao cấp	https://upload.wikimedia.org/wikipedia/commons/c/ca/Sony_logo.svg	Sony	\N
11	\N	Phụ kiện sạc dự phòng, cáp sạc	https://d2zv4gzhlr4ud6.cloudfront.net/media/pictures/tagged_items/540x0/21_ANKERDIRECT/01.jpg	Anker	\N
12	\N	Phụ kiện điện thoại chất lượng cao	\N	Baseus	\N
13	\N	Tai nghe và loa Bluetooth	https://upload.wikimedia.org/wikipedia/commons/c/c1/JBL_Logo.svg	JBL	\N
14	\N	Tai nghe cao cấp của Apple	https://upload.wikimedia.org/wikipedia/commons/9/90/Beats_Electronics_logo.svg	Beats	\N
15	\N	Tai nghe không dây của Apple	\N	AirPods	\N
\.


--
-- TOC entry 4080 (class 0 OID 16581)
-- Dependencies: 222
-- Data for Name: cart_items; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.cart_items (id, created_at, quantity, updated_at, cart_id, product_id) FROM stdin;
\.


--
-- TOC entry 4082 (class 0 OID 16588)
-- Dependencies: 224
-- Data for Name: carts; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.carts (id, created_at, updated_at, user_id) FROM stdin;
1	\N	\N	1
2	\N	\N	2
3	\N	\N	3
\.


--
-- TOC entry 4084 (class 0 OID 16595)
-- Dependencies: 226
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.categories (id, created_at, description, name, updated_at, parent_id) FROM stdin;
1	\N	Máy tính xách tay	Laptop	\N	\N
2	\N	Tai nghe không dây và có dây	Tai nghe	\N	\N
3	\N	Máy tính bảng và iPad	Máy tính bảng	\N	\N
4	\N	Máy tính xách tay	Laptop	\N	\N
5	\N	Smartwatch và fitness tracker	Đồng hồ thông minh	\N	\N
11	\N	Điện thoại iPhone của Apple	iPhone	\N	1
12	\N	Điện thoại Samsung	Samsung	\N	1
13	\N	Điện thoại Xiaomi	Xiaomi	\N	1
14	\N	Điện thoại OPPO	OPPO	\N	1
15	\N	Điện thoại Vivo	Vivo	\N	1
21	\N	Ốp lưng bảo vệ điện thoại	Ốp lưng	\N	2
22	\N	Sạc dự phòng, cáp sạc	Sạc và cáp	\N	2
23	\N	Tai nghe có dây và không dây	Tai nghe	\N	2
24	\N	Miếng dán kính cường lực	Kính cường lực	\N	2
25	\N	Giá đỡ điện thoại, tripod	Giá đỡ	\N	2
\.


--
-- TOC entry 4086 (class 0 OID 16604)
-- Dependencies: 228
-- Data for Name: order_items; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.order_items (id, created_at, price, quantity, order_id, product_id) FROM stdin;
\.


--
-- TOC entry 4088 (class 0 OID 16611)
-- Dependencies: 230
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.orders (id, city, created_at, email, order_code, payment_method, phone_number, recipient_name, status, street_address, total_amount, updated_at, user_id, voucher_id) FROM stdin;
\.


--
-- TOC entry 4090 (class 0 OID 16622)
-- Dependencies: 232
-- Data for Name: password_reset_tokens; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.password_reset_tokens (id, created_at, expiry_date, token, user_id) FROM stdin;
\.


--
-- TOC entry 4092 (class 0 OID 16629)
-- Dependencies: 234
-- Data for Name: product_images; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.product_images (id, alt_text, created_at, image_url, is_primary, product_id) FROM stdin;
\.


--
-- TOC entry 4094 (class 0 OID 16638)
-- Dependencies: 236
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.products (id, created_at, description, name, price, specifications, status, stock_quantity, thumbnail_url, updated_at, brand_id, category_id) FROM stdin;
5	\N	iPhone 15 Pro Max với chip A17 Pro mạnh mẽ, camera 48MP, màn hình Super Retina XDR 6.7 inch	iPhone 15 Pro Max 256GB	32990000.00	{"ram": "8GB", "chip": "A17 Pro", "camera": "48MP", "screen": "6.7 inch", "battery": "4422mAh", "storage": "256GB"}	t	50	https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-max-blue-1.jpg	\N	1	11
6	\N	iPhone 15 Pro với khung titan, Dynamic Island, camera 48MP	iPhone 15 Pro 128GB	27990000.00	{"ram": "8GB", "chip": "A17 Pro", "camera": "48MP", "screen": "6.1 inch", "battery": "3274mAh", "storage": "128GB"}	t	45	https://cdn.tgdd.vn/Products/Images/42/305658/iphone-15-pro-natural-1.jpg	\N	1	11
7	\N	iPhone 15 Plus màn hình lớn 6.7 inch, camera 48MP	iPhone 15 Plus 128GB	24990000.00	{"ram": "6GB", "chip": "A16 Bionic", "camera": "48MP", "screen": "6.7 inch", "battery": "4383mAh", "storage": "128GB"}	t	60	https://cdn.tgdd.vn/Products/Images/42/303891/iphone-15-plus-128gb-blue-1.jpg	\N	1	11
8	\N	iPhone 14 với chip A15 Bionic, camera kép 12MP	iPhone 14 128GB	18990000.00	{"ram": "6GB", "chip": "A15 Bionic", "camera": "12MP", "screen": "6.1 inch", "battery": "3279mAh", "storage": "128GB"}	t	80	https://cdn.tgdd.vn/Products/Images/42/289441/iphone-14-blue-1.jpg	\N	1	11
9	\N	iPhone 13 giá tốt, hiệu năng mạnh mẽ	iPhone 13 128GB	15990000.00	{"ram": "4GB", "chip": "A15 Bionic", "camera": "12MP", "screen": "6.1 inch", "battery": "3240mAh", "storage": "128GB"}	t	100	https://cdn.tgdd.vn/Products/Images/42/223602/iphone-13-pink-1.jpg	\N	1	11
10	\N	Galaxy S24 Ultra với S Pen, camera 200MP, chip Snapdragon 8 Gen 3	Samsung Galaxy S24 Ultra 12GB/256GB	29990000.00	{"ram": "12GB", "chip": "Snapdragon 8 Gen 3", "camera": "200MP", "screen": "6.8 inch", "battery": "5000mAh", "storage": "256GB"}	t	40	https://cdn.tgdd.vn/Products/Images/42/307174/samsung-galaxy-s24-ultra-grey-1.jpg	\N	2	12
11	\N	Galaxy S23 Ultra camera 200MP, hiệu năng đỉnh cao	Samsung Galaxy S23 Ultra 8GB/256GB	25990000.00	{"ram": "8GB", "chip": "Snapdragon 8 Gen 2", "camera": "200MP", "screen": "6.8 inch", "battery": "5000mAh", "storage": "256GB"}	t	35	https://cdn.tgdd.vn/Products/Images/42/249948/samsung-galaxy-s23-ultra-1.jpg	\N	2	12
12	\N	Điện thoại gập cao cấp với màn hình Dynamic AMOLED 2X	Samsung Galaxy Z Fold5 12GB/256GB	40990000.00	{"ram": "12GB", "chip": "Snapdragon 8 Gen 2", "camera": "50MP", "screen": "7.6 inch", "battery": "4400mAh", "storage": "256GB"}	t	20	https://cdn.tgdd.vn/Products/Images/42/306174/samsung-galaxy-z-fold5-1.jpg	\N	2	12
13	\N	Điện thoại gập nhỏ gọn, thời trang	Samsung Galaxy Z Flip5 8GB/256GB	23990000.00	{"ram": "8GB", "chip": "Snapdragon 8 Gen 2", "camera": "12MP", "screen": "6.7 inch", "battery": "3700mAh", "storage": "256GB"}	t	25	https://cdn.tgdd.vn/Products/Images/42/306172/samsung-galaxy-z-flip5-1.jpg	\N	2	12
14	\N	Galaxy A54 camera 50MP, pin 5000mAh	Samsung Galaxy A54 5G 8GB/128GB	9990000.00	{"ram": "8GB", "chip": "Exynos 1380", "camera": "50MP", "screen": "6.4 inch", "battery": "5000mAh", "storage": "128GB"}	t	120	https://cdn.tgdd.vn/Products/Images/42/301726/samsung-galaxy-a54-5g-1.jpg	\N	2	12
15	\N	Xiaomi 14 Pro với camera Leica, chip Snapdragon 8 Gen 3	Xiaomi 14 Pro 12GB/512GB	21990000.00	{"ram": "12GB", "chip": "Snapdragon 8 Gen 3", "camera": "50MP", "screen": "6.73 inch", "battery": "4880mAh", "storage": "512GB"}	t	50	https://cdn.tgdd.vn/Products/Images/42/309816/xiaomi-14-pro-1.jpg	\N	3	13
16	\N	Xiaomi 13T Pro camera 50MP, sạc nhanh 120W	Xiaomi 13T Pro 12GB/256GB	13990000.00	{"ram": "12GB", "chip": "Dimensity 9200+", "camera": "50MP", "screen": "6.67 inch", "battery": "5000mAh", "storage": "256GB"}	t	80	https://cdn.tgdd.vn/Products/Images/42/307594/xiaomi-13t-pro-1.jpg	\N	3	13
17	\N	Redmi Note 13 Pro camera 200MP, giá rẻ	Xiaomi Redmi Note 13 Pro 8GB/128GB	7490000.00	{"ram": "8GB", "chip": "Snapdragon 7s Gen 2", "camera": "200MP", "screen": "6.67 inch", "battery": "5100mAh", "storage": "128GB"}	t	150	https://cdn.tgdd.vn/Products/Images/42/309816/xiaomi-redmi-note-13-pro-1.jpg	\N	3	13
18	\N	Redmi 12 pin khủng 5000mAh, giá tốt	Xiaomi Redmi 12 8GB/256GB	4490000.00	{"ram": "8GB", "chip": "Helio G88", "camera": "50MP", "screen": "6.79 inch", "battery": "5000mAh", "storage": "256GB"}	t	200	https://cdn.tgdd.vn/Products/Images/42/307127/xiaomi-redmi-12-1.jpg	\N	3	13
19	\N	OPPO Find N3 Flip điện thoại gập camera 50MP	OPPO Find N3 Flip 12GB/256GB	22990000.00	{"ram": "12GB", "chip": "Dimensity 9200", "camera": "50MP", "screen": "6.8 inch", "battery": "4300mAh", "storage": "256GB"}	t	30	https://cdn.tgdd.vn/Products/Images/42/309321/oppo-find-n3-flip-1.jpg	\N	4	14
20	\N	OPPO Reno11 F camera 64MP, thiết kế đẹp	OPPO Reno11 F 5G 8GB/256GB	8990000.00	{"ram": "8GB", "chip": "Dimensity 7050", "camera": "64MP", "screen": "6.7 inch", "battery": "5000mAh", "storage": "256GB"}	t	100	https://cdn.tgdd.vn/Products/Images/42/309914/oppo-reno11-f-1.jpg	\N	4	14
21	\N	OPPO A78 pin 5000mAh, sạc nhanh 67W	OPPO A78 8GB/256GB	6490000.00	{"ram": "8GB", "chip": "Snapdragon 680", "camera": "50MP", "screen": "6.43 inch", "battery": "5000mAh", "storage": "256GB"}	t	150	https://cdn.tgdd.vn/Products/Images/42/301816/oppo-a78-1.jpg	\N	4	14
22	\N	Vivo V29e camera 64MP, thiết kế mỏng nhẹ	Vivo V29e 5G 12GB/256GB	8490000.00	{"ram": "12GB", "chip": "Snapdragon 695", "camera": "64MP", "screen": "6.67 inch", "battery": "4800mAh", "storage": "256GB"}	t	90	https://cdn.tgdd.vn/Products/Images/42/307951/vivo-v29e-1.jpg	\N	5	15
23	\N	Vivo Y36 pin 5000mAh, hiệu năng tốt	Vivo Y36 8GB/128GB	5990000.00	{"ram": "8GB", "chip": "Snapdragon 680", "camera": "50MP", "screen": "6.64 inch", "battery": "5000mAh", "storage": "128GB"}	t	120	https://cdn.tgdd.vn/Products/Images/42/307421/vivo-y36-1.jpg	\N	5	15
24	\N	iPad Pro M2 với chip M2 mạnh mẽ, màn hình Liquid Retina	iPad Pro M2 11 inch WiFi 128GB	21990000.00	{"os": "iPadOS 16", "ram": "8GB", "chip": "Apple M2", "screen": "11 inch", "storage": "128GB"}	t	40	https://cdn.tgdd.vn/Products/Images/522/294184/ipad-pro-11-2022-m2-wifi-1.jpg	\N	1	3
25	\N	iPad Air 5 với chip M1, hỗ trợ Apple Pencil 2	iPad Air 5 M1 WiFi 64GB	14990000.00	{"os": "iPadOS 15", "ram": "8GB", "chip": "Apple M1", "screen": "10.9 inch", "storage": "64GB"}	t	50	https://cdn.tgdd.vn/Products/Images/522/285589/ipad-air-5-2022-m1-wifi-1.jpg	\N	1	3
26	\N	iPad Gen 10 màn hình 10.9 inch, chip A14 Bionic	iPad Gen 10 WiFi 64GB	10990000.00	{"os": "iPadOS 16", "ram": "4GB", "chip": "A14 Bionic", "screen": "10.9 inch", "storage": "64GB"}	t	80	https://cdn.tgdd.vn/Products/Images/522/289686/ipad-gen-10-2022-wifi-1.jpg	\N	1	3
27	\N	Galaxy Tab S9 FE với S Pen, màn hình 10.9 inch	Samsung Galaxy Tab S9 FE 8GB/128GB	10490000.00	{"os": "Android 13", "ram": "8GB", "chip": "Exynos 1380", "screen": "10.9 inch", "storage": "128GB"}	t	60	https://cdn.tgdd.vn/Products/Images/522/309126/samsung-galaxy-tab-s9-fe-1.jpg	\N	2	3
28	\N	Ốp lưng silicone hỗ trợ MagSafe cho iPhone 15 Pro Max	Ốp lưng iPhone 15 Pro Max MagSafe	890000.00	{"color": "Nhiều màu", "features": "MagSafe", "material": "Silicone"}	t	300	\N	\N	1	21
29	\N	Ốp lưng trong suốt chống sốc cho Galaxy S24 Ultra	Ốp lưng Samsung S24 Ultra Clear	450000.00	{"color": "Trong suốt", "features": "Chống sốc", "material": "TPU"}	t	250	\N	\N	2	21
30	\N	Ốp lưng sợi Carbon cao cấp cho Xiaomi 14 Pro	Ốp lưng Xiaomi 14 Pro Carbon	350000.00	{"color": "Đen", "features": "Chống va đập", "material": "Carbon"}	t	200	\N	\N	3	21
31	\N	Củ sạc nhanh 20W chính hãng Apple với cổng USB-C	Sạc nhanh Apple 20W USB-C	490000.00	{"port": "USB-C", "power": "20W", "compatible": "iPhone 8 trở lên"}	t	500	\N	\N	1	22
32	\N	Cáp sạc Lightning to USB-C chính hãng Apple	Cáp Lightning to USB-C 1m	490000.00	{"length": "1m", "support": "Fast charging", "connector": "Lightning to USB-C"}	t	600	\N	\N	1	22
33	\N	Sạc dự phòng Anker PowerCore III Elite dung lượng 20000mAh, sạc nhanh 65W	Sạc dự phòng Anker 20000mAh 65W	1990000.00	{"ports": "2x USB-C, 1x USB-A", "power": "65W", "capacity": "20000mAh"}	t	150	\N	\N	11	22
34	\N	Củ sạc GaN 100W nhỏ gọn với 4 cổng sạc	Sạc nhanh Baseus GaN 100W	890000.00	{"ports": "3x USB-C, 1x USB-A", "power": "100W", "technology": "GaN"}	t	200	\N	\N	12	22
35	\N	Cáp sạc nhanh USB-C to USB-C 100W, dài 2m	Cáp USB-C to USB-C 2m Baseus	250000.00	{"power": "100W", "length": "2m", "connector": "USB-C to USB-C"}	t	400	\N	\N	12	22
36	\N	Tai nghe AirPods Pro thế hệ 2 với chip H2, chống ồn chủ động	AirPods Pro 2 USB-C	5990000.00	{"type": "In-ear", "battery": "6 giờ", "features": "ANC, Spatial Audio", "connectivity": "Bluetooth 5.3"}	t	100	\N	\N	15	23
37	\N	Tai nghe AirPods thế hệ 3 với Spatial Audio	AirPods 3	4490000.00	{"type": "Open-ear", "battery": "6 giờ", "features": "Spatial Audio", "connectivity": "Bluetooth 5.0"}	t	120	\N	\N	15	23
38	\N	Tai nghe Galaxy Buds2 Pro chống ồn, âm thanh Hi-Fi	Samsung Galaxy Buds2 Pro	3990000.00	{"type": "In-ear", "battery": "5 giờ", "features": "ANC, 360 Audio", "connectivity": "Bluetooth 5.3"}	t	90	\N	\N	2	23
39	\N	Tai nghe JBL Tune 230NC chống ồn chủ động	JBL Tune 230NC TWS	1790000.00	{"type": "In-ear", "battery": "8 giờ", "features": "ANC", "connectivity": "Bluetooth 5.3"}	t	150	\N	\N	13	23
40	\N	Tai nghe Beats Studio Buds+ chống ồn, âm bass mạnh	Beats Studio Buds+	3490000.00	{"type": "In-ear", "battery": "9 giờ", "features": "ANC, Transparency", "connectivity": "Bluetooth 5.3"}	t	80	\N	\N	14	23
41	\N	Miếng dán kính cường lực full màn hình cho iPhone 15 Pro Max	Kính cường lực iPhone 15 Pro Max	290000.00	{"coverage": "Full screen", "features": "Chống vỡ, chống xước", "hardness": "9H"}	t	500	\N	\N	1	24
42	\N	Kính cường lực UV cho Galaxy S24 Ultra, bảo vệ toàn diện	Kính cường lực Samsung S24 Ultra UV	390000.00	{"coverage": "Full screen", "features": "UV glue, chống vân tay", "hardness": "9H"}	t	300	\N	\N	2	24
43	\N	Đế sạc không dây MagSafe 3 trong 1 cho iPhone, AirPods, Apple Watch	Giá đỡ điện thoại MagSafe 3in1	1290000.00	{"type": "Wireless charger stand", "devices": "3 devices", "features": "MagSafe, Fast charging"}	t	100	\N	\N	11	25
44	\N	Gậy chụp ảnh kiêm tripod điều khiển Bluetooth	Tripod mini Bluetooth	250000.00	{"height": "Tối đa 70cm", "weight": "150g", "features": "Bluetooth remote"}	t	400	\N	\N	12	25
\.


--
-- TOC entry 4096 (class 0 OID 16647)
-- Dependencies: 238
-- Data for Name: review_likes; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.review_likes (id, created_at, review_id, user_id) FROM stdin;
\.


--
-- TOC entry 4098 (class 0 OID 16654)
-- Dependencies: 240
-- Data for Name: reviews; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.reviews (id, comment, created_at, rating, updated_at, product_id, user_id) FROM stdin;
\.


--
-- TOC entry 4100 (class 0 OID 16664)
-- Dependencies: 242
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.users (id, created_at, email, full_name, password_hash, phone_number, role, status, updated_at, username) FROM stdin;
2	2025-10-08 21:02:07.226022	22162054@student.hcmute.edu.vn	Nguyễn Tuấn Tú	$2a$12$K1xcMfEGJyMRhnUQTcEtq.rjEiaJjS2d/VzNNG8dclrQK99nu/UWC	\N	customer	active	2025-10-08 21:02:07.226038	tuantusocial
5	2025-10-08 23:50:58.093087	testuser1@example.com	Test User	$2a$12$mp74Ki80NuitvreFyteJnu8SS6zKDGmXcurGXFmdosZkngaiMoqqe	\N	customer	active	2025-10-08 23:50:58.093174	testuser1
6	2025-10-08 23:51:16.631469	testuser2@example.com	Test User	$2a$12$nF.o/WF1OgBB8YnIFtq5AetQfX8ZsPQtZ/mrKkThiZi08zUSpeSz.	\N	customer	active	2025-10-08 23:51:16.631662	testuser2
7	2025-10-08 23:51:21.160148	testuser3@example.com	Test User	$2a$12$6exWEfmuVSaY5XtPz.jPQefACvvtsDHXfXW40y5rzeCAUapp6XCr6	\N	customer	active	2025-10-08 23:51:21.160175	testuser3
8	2025-10-08 23:51:27.408971	testuser4@example.com	Test User	$2a$12$UYEc2mryiPVR.H9YZ/hplunBDv9jadN0LXRVEhwrzmrgXuPamYI.W	\N	customer	active	2025-10-08 23:51:27.409003	testuser4
9	2025-10-08 23:51:32.495653	testuser5@example.com	Test User	$2a$12$mPdFzWK.TKsOUu/e8m7a0.nT5yPQQgIhEjG.H7p7Kv4NECCUam6.S	\N	customer	active	2025-10-08 23:51:32.495702	testuser5
10	2025-10-08 23:51:38.188063	testuser6@example.com	Test User	$2a$12$7ZVNtI1BwuiIp8YSQ5Nn7uCBoCC1X4gRqLlkSyCf7cefR/R4E8kEK	\N	customer	active	2025-10-08 23:51:38.188102	testuser6
12	2025-10-09 09:05:42.425744	kienhung.do1105@gmail.com	do kien hung	$2a$12$4CfduskWXs6ibOwr3D.bquTTgbco06rBrxXnuZm7FyeoCuvU0kbqa	\N	customer	active	2025-10-09 09:05:42.425774	kienhung
13	2025-10-09 15:49:45.315302	test@example.com	Test User	$2a$12$TX8n3ZKWdqvfMEEivWdd0O2dePa/SUEhxFHZ/PDkWZzORFez1pP/a	\N	customer	active	2025-10-09 15:49:45.315335	testuser
4	2025-10-08 23:33:53.649689	tuantu.career@gmail.com	QTV Updated	$2a$12$9Zn/1csoEjWgRN1EiZqrqOltZjDSSkqhDooh.Q5wfM9ZXp5Mf0rdS	0987654321	admin	active	2025-10-09 16:26:23.79936	admin
3	2025-10-08 21:16:34.893134	admin@test.com	Updated Test Admin	$2a$12$cv05WGx0acnYfhUKpkQVpOPe2IVMMR67T7Emn1OslIeL6TXPpTMvC	0987654321	customer	active	2025-10-09 16:52:14.020298	testadmin
14	2025-10-09 17:20:14.258204	dinhkhanhvn.2004@gmail.com	Tào	$2a$12$HopocAdscEOPwYaSgs8.B.UvVcGrBc/u4CUzXTxPC.kdMVotISpDi	\N	customer	active	2025-10-09 17:20:14.25822	taongu
1	2025-10-08 19:04:32.377318	ttgaming1999@gmail.com	Nguyễn Tuấn Tú	$2a$12$iFs7fRgloaah4NQCxZM4ZuunzdfwtxaeQyFOqs5EqCS3y4Dio5NHm		customer	active	2025-10-09 18:21:19.165457	tuantujr
15	2025-10-10 02:26:15.808063	tu01699963463@gmail.com	Người Thích Đùa	$2a$12$TFz0svozpi.s5X12ANu4s.zBO6VzHdQ6aDbA0rNzDNe0E4kKMJlZq	\N	customer	active	2025-10-10 02:26:15.808084	tu01699963463
\.


--
-- TOC entry 4102 (class 0 OID 16675)
-- Dependencies: 244
-- Data for Name: vouchers; Type: TABLE DATA; Schema: public; Owner: utephonehub_user
--

COPY public.vouchers (id, code, created_at, discount_type, discount_value, expiry_date, max_usage, min_order_value, status, updated_at) FROM stdin;
\.


--
-- TOC entry 4123 (class 0 OID 0)
-- Dependencies: 217
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.addresses_id_seq', 16, true);


--
-- TOC entry 4124 (class 0 OID 0)
-- Dependencies: 219
-- Name: brands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.brands_id_seq', 15, true);


--
-- TOC entry 4125 (class 0 OID 0)
-- Dependencies: 221
-- Name: cart_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.cart_items_id_seq', 3, true);


--
-- TOC entry 4126 (class 0 OID 0)
-- Dependencies: 223
-- Name: carts_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.carts_id_seq', 13, true);


--
-- TOC entry 4127 (class 0 OID 0)
-- Dependencies: 225
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.categories_id_seq', 25, true);


--
-- TOC entry 4128 (class 0 OID 0)
-- Dependencies: 227
-- Name: order_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.order_items_id_seq', 15, true);


--
-- TOC entry 4129 (class 0 OID 0)
-- Dependencies: 229
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.orders_id_seq', 2, true);


--
-- TOC entry 4130 (class 0 OID 0)
-- Dependencies: 231
-- Name: password_reset_tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.password_reset_tokens_id_seq', 1, false);


--
-- TOC entry 4131 (class 0 OID 0)
-- Dependencies: 233
-- Name: product_images_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.product_images_id_seq', 10, true);


--
-- TOC entry 4132 (class 0 OID 0)
-- Dependencies: 235
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.products_id_seq', 44, true);


--
-- TOC entry 4133 (class 0 OID 0)
-- Dependencies: 237
-- Name: review_likes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.review_likes_id_seq', 1, false);


--
-- TOC entry 4134 (class 0 OID 0)
-- Dependencies: 239
-- Name: reviews_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.reviews_id_seq', 1, false);


--
-- TOC entry 4135 (class 0 OID 0)
-- Dependencies: 241
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.users_id_seq', 15, true);


--
-- TOC entry 4136 (class 0 OID 0)
-- Dependencies: 243
-- Name: vouchers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: utephonehub_user
--

SELECT pg_catalog.setval('public.vouchers_id_seq', 1, true);


--
-- TOC entry 3874 (class 2606 OID 16570)
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- TOC entry 3876 (class 2606 OID 16579)
-- Name: brands brands_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.brands
    ADD CONSTRAINT brands_pkey PRIMARY KEY (id);


--
-- TOC entry 3878 (class 2606 OID 16586)
-- Name: cart_items cart_items_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT cart_items_pkey PRIMARY KEY (id);


--
-- TOC entry 3880 (class 2606 OID 16593)
-- Name: carts carts_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT carts_pkey PRIMARY KEY (id);


--
-- TOC entry 3884 (class 2606 OID 16602)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- TOC entry 3886 (class 2606 OID 16609)
-- Name: order_items order_items_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT order_items_pkey PRIMARY KEY (id);


--
-- TOC entry 3888 (class 2606 OID 16620)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- TOC entry 3892 (class 2606 OID 16627)
-- Name: password_reset_tokens password_reset_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.password_reset_tokens
    ADD CONSTRAINT password_reset_tokens_pkey PRIMARY KEY (id);


--
-- TOC entry 3896 (class 2606 OID 16636)
-- Name: product_images product_images_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.product_images
    ADD CONSTRAINT product_images_pkey PRIMARY KEY (id);


--
-- TOC entry 3898 (class 2606 OID 16645)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 3900 (class 2606 OID 16652)
-- Name: review_likes review_likes_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.review_likes
    ADD CONSTRAINT review_likes_pkey PRIMARY KEY (id);


--
-- TOC entry 3902 (class 2606 OID 16662)
-- Name: reviews reviews_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);


--
-- TOC entry 3910 (class 2606 OID 16696)
-- Name: vouchers uk_30ftp2biebbvpik8e49wlmady; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.vouchers
    ADD CONSTRAINT uk_30ftp2biebbvpik8e49wlmady UNIQUE (code);


--
-- TOC entry 3882 (class 2606 OID 16686)
-- Name: carts uk_64t7ox312pqal3p7fg9o503c2; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT uk_64t7ox312pqal3p7fg9o503c2 UNIQUE (user_id);


--
-- TOC entry 3904 (class 2606 OID 16692)
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 3894 (class 2606 OID 16690)
-- Name: password_reset_tokens uk_71lqwbwtklmljk3qlsugr1mig; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.password_reset_tokens
    ADD CONSTRAINT uk_71lqwbwtklmljk3qlsugr1mig UNIQUE (token);


--
-- TOC entry 3890 (class 2606 OID 16688)
-- Name: orders uk_dhk2umg8ijjkg4njg6891trit; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT uk_dhk2umg8ijjkg4njg6891trit UNIQUE (order_code);


--
-- TOC entry 3906 (class 2606 OID 16694)
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 3908 (class 2606 OID 16673)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3912 (class 2606 OID 16684)
-- Name: vouchers vouchers_pkey; Type: CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.vouchers
    ADD CONSTRAINT vouchers_pkey PRIMARY KEY (id);


--
-- TOC entry 3913 (class 2606 OID 16697)
-- Name: addresses fk1fa36y2oqhao3wgg2rw1pi459; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.addresses
    ADD CONSTRAINT fk1fa36y2oqhao3wgg2rw1pi459 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3914 (class 2606 OID 16707)
-- Name: cart_items fk1re40cjegsfvw58xrkdp6bac6; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fk1re40cjegsfvw58xrkdp6bac6 FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 3920 (class 2606 OID 16732)
-- Name: orders fk32ql8ubntj5uh44ph9659tiih; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3924 (class 2606 OID 16752)
-- Name: products fka3a4mpsfdf4d2y6r8ra3sc8mv; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fka3a4mpsfdf4d2y6r8ra3sc8mv FOREIGN KEY (brand_id) REFERENCES public.brands(id);


--
-- TOC entry 3916 (class 2606 OID 16712)
-- Name: carts fkb5o626f86h46m4s7ms6ginnop; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.carts
    ADD CONSTRAINT fkb5o626f86h46m4s7ms6ginnop FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3918 (class 2606 OID 16722)
-- Name: order_items fkbioxgbv59vetrxe0ejfubep1w; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES public.orders(id);


--
-- TOC entry 3928 (class 2606 OID 16777)
-- Name: reviews fkcgy7qjc1r99dp117y9en6lxye; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkcgy7qjc1r99dp117y9en6lxye FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3921 (class 2606 OID 16737)
-- Name: orders fkdimvsocblb17f45ikjr6xn1wj; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkdimvsocblb17f45ikjr6xn1wj FOREIGN KEY (voucher_id) REFERENCES public.vouchers(id);


--
-- TOC entry 3922 (class 2606 OID 16742)
-- Name: password_reset_tokens fkk3ndxg5xp6v7wd4gjyusp15gq; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.password_reset_tokens
    ADD CONSTRAINT fkk3ndxg5xp6v7wd4gjyusp15gq FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3926 (class 2606 OID 16762)
-- Name: review_likes fkm2uonfg8ky6jwtu6iugkilox8; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.review_likes
    ADD CONSTRAINT fkm2uonfg8ky6jwtu6iugkilox8 FOREIGN KEY (review_id) REFERENCES public.reviews(id);


--
-- TOC entry 3927 (class 2606 OID 16767)
-- Name: review_likes fknual15vv88tiqnwmi60tb2l8d; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.review_likes
    ADD CONSTRAINT fknual15vv88tiqnwmi60tb2l8d FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 3919 (class 2606 OID 16727)
-- Name: order_items fkocimc7dtr037rh4ls4l95nlfi; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.order_items
    ADD CONSTRAINT fkocimc7dtr037rh4ls4l95nlfi FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 3925 (class 2606 OID 16757)
-- Name: products fkog2rp4qthbtt2lfyhfo32lsw9; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id) REFERENCES public.categories(id);


--
-- TOC entry 3915 (class 2606 OID 16702)
-- Name: cart_items fkpcttvuq4mxppo8sxggjtn5i2c; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.cart_items
    ADD CONSTRAINT fkpcttvuq4mxppo8sxggjtn5i2c FOREIGN KEY (cart_id) REFERENCES public.carts(id);


--
-- TOC entry 3929 (class 2606 OID 16772)
-- Name: reviews fkpl51cejpw4gy5swfar8br9ngi; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fkpl51cejpw4gy5swfar8br9ngi FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 3923 (class 2606 OID 16747)
-- Name: product_images fkqnq71xsohugpqwf3c9gxmsuy; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.product_images
    ADD CONSTRAINT fkqnq71xsohugpqwf3c9gxmsuy FOREIGN KEY (product_id) REFERENCES public.products(id);


--
-- TOC entry 3917 (class 2606 OID 16717)
-- Name: categories fksaok720gsu4u2wrgbk10b5n8d; Type: FK CONSTRAINT; Schema: public; Owner: utephonehub_user
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT fksaok720gsu4u2wrgbk10b5n8d FOREIGN KEY (parent_id) REFERENCES public.categories(id);


--
-- TOC entry 4108 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

GRANT ALL ON SCHEMA public TO cloudsqlsuperuser;


-- Completed on 2025-10-10 10:14:44

--
-- PostgreSQL database dump complete
--

